package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Data
@Accessors(chain=true)
public class Order {
    private String orderNo;

    private String memId;

    private Integer courseId;

    private BigDecimal amount;

    private Integer count;

    private Byte tradeState;

    private String desc;

}