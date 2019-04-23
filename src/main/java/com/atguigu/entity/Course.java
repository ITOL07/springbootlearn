package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain=true)
public class Course {
    private String courseId;

    private Integer type;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private Byte status;

    private String coachId;

    private Byte coachType;

    private String clubId;

    private Byte clubType;

    private String try_flag;

    private String bz1;

    private String bz2;

}