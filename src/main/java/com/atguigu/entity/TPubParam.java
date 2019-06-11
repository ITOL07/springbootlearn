package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Accessors(chain=true)
public class TPubParam {
    private String paraId;

    private String paraName;

    private Integer minVal;

    private Integer maxVal;

    private BigDecimal pct;

    private Date curTime;

    private String bz1;

    private Date upTime;

}