package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain=true)
public class CoachLesson {
    private String coachId;

    private String courseId;

    private Byte seqNo;

    private String clubId;

    private Byte status;

    private Date startTime1;

    private Date endTime1;

    private Date startTime2;

    private Date endTime2;

    private String memId;

    private String bz1;
}