package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain=true)
public class TCoachLessonReg {
    private Integer id;

    private String coachId;

    private Integer lesCount;

    private BigDecimal lesPrice;

    private Integer soldCount;

    private BigDecimal soldPrice;

    private Date regDate;

    private Date regTime;

    private Date chTime;

    private String courseType;


}