package com.suqiu.service;

import java.util.Map;

public interface WeixinPayService {

    /***
     * 查询订单状态
     * @param out_trade_no : 客户端自定义订单编号
     * @return
     */
    public Map queryPayStatus(String out_trade_no);
    /*****
     * 创建二维码
     * @param parameterMap:传入的参数（订单编号，支付金额等）
     * @return
     */
    public Map createNative(Map<String,String> parameterMap);
}
