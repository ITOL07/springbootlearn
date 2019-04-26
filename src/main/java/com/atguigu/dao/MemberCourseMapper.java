package com.atguigu.dao;

import com.atguigu.entity.MemberCourse;

import java.util.Map;

public interface MemberCourseMapper {
    int deleteByPrimaryKey(String kcId);

    int insert(MemberCourse record);

    int insertSelective(MemberCourse record);

    MemberCourse selectByPrimaryKey(String kcId);
    MemberCourse selectByMemId(String memId);

    String selectMaxKcId();

    int updateByPrimaryKeySelective(MemberCourse record);

    int updateByPrimaryKey(MemberCourse record);
    Map<Object,Object> selectMemCourseSum(String mem_id);

    boolean updateStatus(MemberCourse record);

    boolean updateMemberNum(MemberCourse record);
}