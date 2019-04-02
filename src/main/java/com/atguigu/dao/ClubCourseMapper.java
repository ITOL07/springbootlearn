package com.atguigu.dao;

import com.atguigu.entity.ClubCourse;

public interface ClubCourseMapper {
    int insert(ClubCourse record);

    int insertSelective(ClubCourse record);
}