package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class CoachCourse {
    private String coachId;

    private String courseId;

    private Byte totalLesson;

    private Byte used;

    private Byte rem;

    private String clubId;

    private Byte status;
}