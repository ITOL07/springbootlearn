package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Accessors(chain=true)
public class Member {
    private String memId;

    private String no;

    private String nickName;

    private Byte age;

    private String tel;

    private BigDecimal height;

    private BigDecimal weight;

    private Date birthday;

    private String sex;

    private String icon;

    private String name;

    private Byte type;
}