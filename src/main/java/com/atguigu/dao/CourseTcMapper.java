package com.atguigu.dao;

import com.atguigu.entity.CourseTc;

public interface CourseTcMapper {
    int deleteByPrimaryKey(String courseId);

    int insert(CourseTc record);

    int insertSelective(CourseTc record);

    CourseTc selectByPrimaryKey(String courseId);

    int updateByPrimaryKeySelective(CourseTc record);

    int updateByPrimaryKey(CourseTc record);
}