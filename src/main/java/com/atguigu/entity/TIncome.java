package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain=true)
public class TIncome {
    private String userId;

    private String userType;

    private Date regDate;

    private BigDecimal ktPer;

    private BigDecimal ktSum;

    private BigDecimal ktCnt;

    private BigDecimal xtPer;

    private BigDecimal xtSum;

    private BigDecimal xtCnt;

    private String bz1;

    private String bz2;

    private Date regTime;

}