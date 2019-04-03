package com.atguigu.service;


import com.atguigu.entity.CoachCourse;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CoachCourseService {
    public CoachCourse getCoachCourseById(@Param("coachId")int coachId);

    public Map<Object,Object> selecttest(@Param("coachId")int coachId);

    public CoachCourse selecttest1(@Param("coachId")int coachId);
}
