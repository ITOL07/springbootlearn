package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain=true)
public class TIncomeDtl {
    private String courseId;

    private String coachId;

    private String clubId;

    private Date regDate;

    private Integer ktCnt;

    private Integer xtCnt;

    private String bz1;

    private String bz2;

    private Date regTime;

}