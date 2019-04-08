package com.atguigu.dao;

import com.atguigu.entity.CourseInfo;

public interface CourseInfoMapper {
    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);
}