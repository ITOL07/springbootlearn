package com.atguigu.dao;

import com.atguigu.entity.MemberCourse;

public interface MemberCourseMapper {
    int deleteByPrimaryKey(String memId);

    int insert(MemberCourse record);

    int insertSelective(MemberCourse record);

    MemberCourse selectByPrimaryKey(String memId);

    int updateByPrimaryKeySelective(MemberCourse record);

    int updateByPrimaryKey(MemberCourse record);
}