package com.suqiu.order.controller;

import com.suqiu.model.req.CloseOrderModel;
import com.suqiu.model.req.DeleteOrderModel;
import com.suqiu.model.req.DeliveryOrderModel;
import com.suqiu.order.pojo.*;
import com.suqiu.order.service.OrderItemService;
import com.suqiu.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import entity.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/delivery")
    public JsonDTO delivery(@RequestBody DeliveryOrderModel model) {
        orderService.delivery(model);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("发货成功");
    }

    @PostMapping(value = "/delete")
    public JsonDTO delete(@RequestBody DeleteOrderModel model) {
        //调用OrderService实现根据主键删除
        orderService.delete(model);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("删除订单");
    }

    @PostMapping(value = "/close")
    public JsonDTO closeOrder(@RequestBody CloseOrderModel closeOrderModel) {
        orderService.closeOrder(closeOrderModel);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("关闭订单");
    }

    /***
     * Order分页条件搜索实现
     * @param order
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Order order, @PathVariable int page, @PathVariable int size) {
        //调用OrderService实现分页条件查询Order
        PageInfo<Order> pageInfo = orderService.findPage(order, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 订单列表 @zero
     * @param queryParam 查询条件
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        Order order = new Order();
        order.setId(queryParam.getOrderSn());//订单号
        order.setReceiverMobile(queryParam.getReceiverKeyword());//手机号
        PageInfo<Order> pageInfo = orderService.findPage(order, pageNum, pageSize);
        List<Order> list = pageInfo.getList();
        if (CommonUtil.isNull(list)) {
            return CommonResult.failed("查无记录");
        }

        List<OmsOrder> orderList = new ArrayList<>();
        list.forEach(t -> {
            OmsOrder omsOrder = new OmsOrder();
            omsOrder.setId(Long.parseLong(t.getId()));
            omsOrder.setOrderSn(t.getId());
            omsOrder.setCreateTime(t.getCreateTime());
            omsOrder.setMemberUsername(t.getUsername());
            omsOrder.setTotalAmount(new BigDecimal(t.getTotalMoney()));
            omsOrder.setPayAmount(new BigDecimal(t.getPayMoney()));
            omsOrder.setPayType(1);
            omsOrder.setSourceType(1);
            omsOrder.setStatus(Integer.parseInt(t.getOrderStatus()));

            orderList.add(omsOrder);
        });

        return CommonResult.success(CommonPage.restPage(orderList));
    }


    /***
     * Order分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用OrderService实现分页查询Order
        PageInfo<Order> pageInfo = orderService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param order
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Order>> findList(@RequestBody(required = false) Order order) {
        //调用OrderService实现条件查询Order
        List<Order> list = orderService.findList(order);
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", list);
    }


    /***
     * 修改Order数据
     * @param order
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Order order, @PathVariable String id) {
        //设置主键值
        order.setId(id);
        //调用OrderService实现修改Order
        orderService.update(order);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Order数据
     * @param order
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Order order) {
        //调用OrderService实现添加Order
        String username = TokenDecode.getUserInfo().get("username");

        order.setUsername(username);

        orderService.add(order);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Order数据
     * @param id
     * @return
     */
//    @GetMapping("/{id}")
//    public Result<Order> findById(@PathVariable String id) {
//        //调用OrderService实现根据主键查询Order
//        Order order = orderService.findById(id);
//        return new Result<Order>(true, StatusCode.OK, "查询成功", order);
//    }


    /***
     * 根据ID查看订单 zero
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public JsonDTO findOrderById(@PathVariable String id){
        //调用OrderService实现根据主键查询Order
        Order order = orderService.findById(id);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        List<OrderItem> list = orderItemService.findList(orderItem);

        OmsOrderDetail orderDetailResult = new OmsOrderDetail();

        orderDetailResult.setId(Long.parseLong(order.getId()));
        orderDetailResult.setOrderSn(order.getId());
        orderDetailResult.setCreateTime(order.getCreateTime());
        orderDetailResult.setMemberUsername(order.getUsername());
        orderDetailResult.setTotalAmount(new BigDecimal(order.getTotalMoney()));
        orderDetailResult.setPayAmount(new BigDecimal(order.getPayMoney()));
        orderDetailResult.setFreightAmount(new BigDecimal(order.getPostFee()));
        orderDetailResult.setPromotionAmount(new BigDecimal(order.getPreMoney()));
        orderDetailResult.setIntegrationAmount(new BigDecimal(0));
        orderDetailResult.setCouponAmount(new BigDecimal(0));
        orderDetailResult.setDiscountAmount(new BigDecimal(0));
        orderDetailResult.setPayType(Integer.parseInt(order.getPayType()));//支付方式（要转换）
        orderDetailResult.setSourceType(Integer.parseInt(order.getSourceType()));//订单来源
        orderDetailResult.setStatus(Integer.parseInt(order.getOrderStatus()));
        orderDetailResult.setOrderType(0);//写死为正常订单
        orderDetailResult.setDeliveryCompany("");
        orderDetailResult.setDeliverySn("");
        orderDetailResult.setAutoConfirmDay(15);
        orderDetailResult.setIntegration(0);
        orderDetailResult.setGrowth(0);
        orderDetailResult.setPromotionInfo("");
        orderDetailResult.setReceiverName(order.getReceiverContact());
        orderDetailResult.setReceiverPhone(order.getReceiverMobile());
        orderDetailResult.setReceiverPostCode("");
        orderDetailResult.setReceiverProvince("");
        orderDetailResult.setReceiverCity("");
        orderDetailResult.setReceiverRegion("");
        orderDetailResult.setReceiverDetailAddress(order.getReceiverAddress());
        orderDetailResult.setNote(order.getBuyerMessage());
        orderDetailResult.setConfirmStatus(Integer.parseInt(order.getConsignStatus()));
        orderDetailResult.setDeleteStatus(0);
        orderDetailResult.setModifyTime(order.getUpdateTime());

//        List<OrderItem> list

        List<OmsOrderItem> orderItemList = new ArrayList<>();
        List<OmsOrderOperateHistory> historyList = new ArrayList<>();

        if (CommonUtil.isNotNull(list)) {
            list.forEach(t -> {
                OmsOrderItem omsOrderItem = new OmsOrderItem();
                omsOrderItem.setId(Long.parseLong(t.getId()));
                omsOrderItem.setOrderId(Long.parseLong(t.getOrderId()));
                omsOrderItem.setProductId(Long.parseLong(t.getName()));
                omsOrderItem.setProductBrand("");
                omsOrderItem.setProductPrice(new BigDecimal(t.getPrice()));
                omsOrderItem.setProductQuantity(t.getNum());
                omsOrderItem.setProductAttr("[{\"key\":\"颜色\",\"value\":\"默认\"},{\"key\":\"规格\",\"value\":\"默认\"}]");

                orderItemList.add(omsOrderItem);

                OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                history.setId(Long.parseLong(t.getId()));
                history.setOrderId(Long.parseLong(t.getOrderId()));
                history.setOperateMan("林庆兴");
                history.setCreateTime(order.getCreateTime());
                history.setOrderStatus(Integer.parseInt(order.getOrderStatus()));//订单状态
                history.setNote(order.getBuyerMessage());

                historyList.add(history);

            });
            Map<String, List<OrderItem>> orderItemMap = list.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        }
        orderDetailResult.setOrderItemList(orderItemList);
        orderDetailResult.setHistoryList(historyList);

//        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("查询订单成功");
        return JsonDTO.createInstance().put("data", orderDetailResult).setStatus(JsonDTO.SUCCESS).setMsg("查询订单成功");
    }


    /***
     * 查询Order全部数据
     * @return
     */
    @GetMapping
    public Result<List<Order>> findAll() {
        //调用OrderService实现查询所有Order
        List<Order> list = orderService.findAll();
        return new Result<List<Order>>(true, StatusCode.OK, "查询成功", list);
    }
}
