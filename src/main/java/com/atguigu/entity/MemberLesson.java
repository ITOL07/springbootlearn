package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class MemberLesson {
    private String memId;

    private String saleId;

    private Integer seqNo;

    private String clubId;

    private Byte status;

    private Date startTime1;

    private Date endTime1;

    private Date startTime2;

    private Date endTime2;

    private String coachId;

    private String realClub;

    private String realCoach;

    private String bz1;

    private String bz2;

    private Date chTime;

    private String kcId;
}