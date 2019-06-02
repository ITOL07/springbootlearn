package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Data
@Accessors(chain=true)
public class ClubCourse {
    private Integer clubId;

    private Integer coachId;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;
}