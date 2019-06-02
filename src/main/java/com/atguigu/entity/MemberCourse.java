package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class MemberCourse {
    private String kcId;

    private String memId;

    private String courseId;

    private Integer totalLesson;

    private Integer used;

    private Integer rem;

    private Byte status;

    private String bz1;

    private Date buy_time;

    private Integer buy_count;
}