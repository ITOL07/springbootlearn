package com.atguigu.controller;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;
import com.atguigu.service.OrderService;
import com.atguigu.wechatpay.app.UnifiedOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class wxpayController {

    @Resource
    private OrderService orderService;

    @PostMapping("/id")
    public  Map<String, Object> wxpay(
            @RequestParam("desc") String desc,
            @RequestParam("order_no") String order_no,
            @RequestParam("openid") String openid
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String money = "0.01";//TODO 测试马路边捡到一分钱
        OrderDtl order = new OrderDtl();

        BigDecimal bigtotalAmount = new BigDecimal(money);

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
}