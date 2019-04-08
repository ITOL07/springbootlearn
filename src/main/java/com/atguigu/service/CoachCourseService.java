package com.atguigu.service;


import com.atguigu.entity.CoachCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CoachCourseService {
    public List<CoachCourse> getCoachCourseById(@Param("coachId")String coachId);

//    public List<Map<Object, Object>> selecttest(@Param("coachId")String coachId);
//
//    public CoachCourse selecttest1(@Param("coachId")String coachId);
}
