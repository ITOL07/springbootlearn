package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Accessors(chain=true)
public class OrderDtl {
    private String orderNo;

    private String memId;

    private String saleId;

    private BigDecimal amount;

    private Integer count;

    private String tradeState;

    private String des;

    private String tradeStateDesc;

    private Date recvTime;

    private String try_flag;

 }