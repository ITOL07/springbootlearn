package com.atguigu.dao;

import com.atguigu.entity.CourseInfo;

public interface CourseInfoMapper {
    int deleteByPrimaryKey(String courseType);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(String courseType);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
}