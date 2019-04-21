package com.atguigu.dao;

import com.atguigu.entity.MemberCourse;

public interface MemberCourseMapper {
    int deleteByPrimaryKey(String kcId);

    int insert(MemberCourse record);

    int insertSelective(MemberCourse record);

    MemberCourse selectByPrimaryKey(String kcId);

    int updateByPrimaryKeySelective(MemberCourse record);

    int updateByPrimaryKey(MemberCourse record);
}