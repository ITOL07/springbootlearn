package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain=true)
public class CourseTc {
    private String courseId;

    private BigDecimal jlKtPer1;

    private BigDecimal jlXtPer1;

    private BigDecimal jlKtPer2;

    private BigDecimal jlXtPer2;

    private BigDecimal cdKt;

    private String bz2;

    private String bz1;

    private Date chTime;

    private BigDecimal jlKtCnt1;

    private BigDecimal jlXtCnt1;

    private BigDecimal jlKtCnt2;

    private BigDecimal jlXtCnt2;
}