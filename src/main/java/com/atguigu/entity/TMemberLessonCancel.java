package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class TMemberLessonCancel extends TMemberLessonCancelKey {
    private String memId;

    private Date startTime1;

    private String courseName;

    private String courseType;

    private String clubName;

    private String coachName;

    private String memName;

    private String memIcon;

    private Integer cancelCount;

    private String cancelUser;

    private Date cancelTime;

    private String cancelReason;

    private Integer cancelState;

    private String coachId;

    private String clubId;

    private Date endTime1;

    private String bz1;
}