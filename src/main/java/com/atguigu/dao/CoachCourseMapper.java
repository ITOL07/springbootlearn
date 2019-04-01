package com.atguigu.dao;

import com.atguigu.entity.CoachCourse;

public interface CoachCourseMapper {
    int deleteByPrimaryKey(Integer coachId);

    int insert(CoachCourse record);

    int insertSelective(CoachCourse record);

    CoachCourse selectByPrimaryKey(Integer coachId);

    int updateByPrimaryKeySelective(CoachCourse record);

    int updateByPrimaryKey(CoachCourse record);
}