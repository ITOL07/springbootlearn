package com.atguigu.dao;

import com.atguigu.entity.CoachCourse;

import java.util.List;

public interface CoachCourseMapper {
    int insert(CoachCourse record);

    int insertSelective(CoachCourse record);

    List<CoachCourse> selectByPrimaryKey(String coach_id);
}