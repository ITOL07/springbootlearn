package com.atguigu.dao;

import com.atguigu.entity.MemberCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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

    Map<Object,Object>  selectMemCourseInfo(@Param("memId")String mem_id, @Param("coachId") String coachId,@Param("clubId") String clubId);
    List<Map<Object,Object>> selectMemCourseInfo1(String memId);
 }