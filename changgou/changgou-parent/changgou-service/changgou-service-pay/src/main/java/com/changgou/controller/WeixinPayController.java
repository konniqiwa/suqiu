package com.changgou.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.service.WeixinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "weixin/pay")
@CrossOrigin
public class WeixinPayController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private WeixinPayService weixinPayService;

    /***
     * 支付回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/notify/url")
    public String notifyUrl(HttpServletRequest request){
        InputStream inStream;
        try {
            //读取支付回调数据
            inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            // 将支付回调数据转换成xml字符串
            String result = new String(outSteam.toByteArray(), "utf-8");
            //将xml字符串转换成Map结构
            Map<String, String> map = WXPayUtil.xmlToMap(result);


            //获取自定义参数数据
            String attach = map.get("attach");
            Map<String,String> attachMap = JSON.parseObject(attach, Map.class);


            System.out.println(map);

            //rabbitTemplate.convertAndSend("exchange.order","queue.order", JSON.toJSONString(map));
            rabbitTemplate.convertAndSend(attachMap.get("exchange"),attachMap.get("routingkey"), JSON.toJSONString(map));

            //响应数据设置
            Map respMap = new HashMap();
            respMap.put("return_code","SUCCESS");
            respMap.put("return_msg","OK");
            return WXPayUtil.mapToXml(respMap);
        } catch (Exception e) {
            e.printStackTrace();
            //记录错误日志
        }
        return null;
    }

    /***
     * 查询支付状态
     * @param out_trade_no
     * @return
     */
    @RequestMapping(value = "/status/query")
    public Result queryStatus(String out_trade_no){
        Map<String,String> resultMap = weixinPayService.queryPayStatus(out_trade_no);
        return new Result(true,StatusCode.OK,"查询状态成功！",resultMap);
    }

    /**
     * 生成二维码连接
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/create/native")
    public Result createNative(@RequestParam Map<String,String> parameter) {

        Map<String,String> result = weixinPayService.createNative(parameter);

        return new Result(true, StatusCode.OK,"获取二维码成功",result);
    }
}
