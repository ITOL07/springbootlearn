package com.atguigu.controller;

import com.atguigu.wechatpay.app.UnifiedOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class wxpayController {
   // private UnifiedOrder uniorder;

    @PostMapping("/id")
    public static Map<String, Object> wxpay(
            @RequestParam("desc") String desc,
            @RequestParam("order_no") String order_no
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String money = "0.01";//TODO 测试马路边捡到一分钱

        BigDecimal bigtotalAmount = new BigDecimal(money);
        //String description = "十万元重要疾病保险";
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        resultMap = UnifiedOrder.weixinPrePay(order_no, bigtotalAmount, desc);
        Map<String, Object> payMap = new HashMap<String, Object>();
        payMap=UnifiedOrder.queryWxResult(order_no);
        System.out.println(payMap);
        return resultMap;
    }
}