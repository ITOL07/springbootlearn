package com.atguigu.dao;

import com.atguigu.entity.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseId);
    List<Course> selectByClubId(String courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}