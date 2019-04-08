package com.atguigu.controller;

import com.atguigu.entity.OrderDtl;
import com.atguigu.service.OrderService;
import com.atguigu.wechatpay.app.UnifiedOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付接口，包括统一下单和订单查询
 */
@RestController
@RequestMapping("/pay")
public class wxpayController {

    @Resource
    private OrderService orderService;

    @PostMapping("/id")
    public  Map<String, Object> wxpay(
            @RequestParam("desc") String desc,
            @RequestParam("order_no") String order_no,
            @RequestParam("openid") String openid,
            @RequestParam("price") String price
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String money = "0.01";//TODO 测试马路边捡到一分钱
        OrderDtl order = new OrderDtl();

        BigDecimal bigtotalAmount = new BigDecimal(price);

        resultMap = UnifiedOrder.weixinPrePay(order_no, bigtotalAmount, desc,openid);
        //插入order表
        System.out.println("order_no==="+order_no+"====openid======"+openid);
        order.setOrderNo(order_no);
        order.setAmount(bigtotalAmount);
        order.setMemId(openid);
        order.setDes(desc);

        boolean bool = this.orderService.addOrder(order);

        Map<String, Object> payMap = new HashMap<String, Object>();
        payMap=UnifiedOrder.queryWxResult(order_no);
        System.out.println(payMap);
        return resultMap;
    }

    @PostMapping("/res")
    public  Map<String, Object> wxpay(
            @RequestParam("order_no") String order_no//,
//            @RequestParam("tradeState") String tradeState
    ){
        Map<String, Object> payMap = new HashMap<String, Object>();
        OrderDtl order = new OrderDtl();
        Date d = new Date();


        //更新order表支付状态
//        System.out.println("order_no==="+order_no+"====tradeState======"+tradeState);
        System.out.println("收到订单号==="+order_no);
        System.out.println("开始查询支付结果==="+order_no);


        payMap=UnifiedOrder.queryWxResult(order_no);
        String trade_state=payMap.get("trade_state").toString();
        String trade_state_desc=payMap.get("trade_state_desc").toString();
        System.out.println("order_no["+order_no+"]\t trade_state["+trade_state+"]\t trade_state_desc"+trade_state_desc);

        order.setOrderNo(order_no);
        if(trade_state.equals("NOTPAY")){
            trade_state="-1";
        }else if(trade_state.equals("SUCCESS")){
            trade_state="0";
        }

        order.setOrderNo(order_no);
        order.setTradeState(trade_state);
        order.setTradeStateDesc(trade_state_desc);
        order.setRecvTime(d);

        boolean bool = this.orderService.updateOrder(order);
        System.out.println("更新数据库状态："+bool);

        return payMap;
    }
}