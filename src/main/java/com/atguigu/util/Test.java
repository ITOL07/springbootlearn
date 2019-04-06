package com.atguigu.util;

import com.atguigu.wechatpay.app.UnifiedOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
//    public static void main(String[] args) {
//        String verifyCode = String
//                .valueOf(new Random().nextInt(8999) + 1000);
//        System.out.println("verifyCode==="+verifyCode);
//    }

    public static void main(String[] args) {
        Map<String, Object> payMap = new HashMap<String, Object>();

        //20190329000002  支付成功的
        //1554474280259173 支付失败的
        String order_no="20190329000002";
        payMap=UnifiedOrder.queryWxResult(order_no);
        System.out.println(payMap);
        System.out.println(payMap.get("trade_state"));
        System.out.println(payMap.get("trade_state_desc"));

        order_no="1554474280259173";
        payMap=UnifiedOrder.queryWxResult(order_no);
        System.out.println(payMap);
        System.out.println(payMap.get("trade_state"));
        System.out.println(payMap.get("trade_state_desc"));
    }
}
