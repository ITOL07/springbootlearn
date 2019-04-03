package com.atguigu.dao;

import com.atguigu.entity.CoachCourse;

import java.util.List;
import java.util.Map;

public interface CoachCourseMapper {
    int deleteByPrimaryKey(Integer coachId);

    int insert(CoachCourse record);

    int insertSelective(CoachCourse record);

    CoachCourse selectByPrimaryKey(Integer coachId);

    List<Map<Object,Object>> selecttest(Integer coachId);

    CoachCourse selecttest1(Integer coachId);

    int updateByPrimaryKeySelective(CoachCourse record);

    int updateByPrimaryKey(CoachCourse record);
}