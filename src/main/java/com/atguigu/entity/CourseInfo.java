package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CourseInfo {
    private String courseType;

    private String courseName;

    private String tryFlag;

    private String bz2;

    private String clubType;

    private String coachType;

    private String brief;

    private String detail;

    private String approp;

    private String courseTime;

    private String suggest;

    private String info_pic;

    private String sale_pic;

    private int min_count;

    private String process;

}