package com.atguigu.dao;

import com.atguigu.entity.CoachLesson;

public interface CoachLessonMapper {
    int insert(CoachLesson record);

    int insertSelective(CoachLesson record);
}