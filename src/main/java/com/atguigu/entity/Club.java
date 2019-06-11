package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Accessors(chain=true)
public class Club {
    private String clubId;

    private String name;

    private BigDecimal la;

    private BigDecimal lo;

    private String icon;

    private String address;

    private String tel;

    private String photo;

    private Byte type;

    private Float area;

    private Date openTime;

    private Date closeTime;

    private String jcss;

}