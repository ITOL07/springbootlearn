package com.atguigu.dao;

import com.atguigu.entity.TMemberLessonCancel;
import com.atguigu.entity.TMemberLessonCancelKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TMemberLessonCancelMapper {
    int deleteByPrimaryKey(TMemberLessonCancelKey key);

    int insert(TMemberLessonCancel record);

    int insertSelective(TMemberLessonCancel record);

    TMemberLessonCancel selectByPrimaryKey(TMemberLessonCancelKey key);

    int updateByPrimaryKeySelective(TMemberLessonCancel record);

    int updateByPrimaryKey(TMemberLessonCancel record);

    List<Map<Object,Object>> selectByAll(@Param("memId") String memId, @Param("coachId") String coachId, @Param("clubId") String clubId, @Param("status")String status, @Param("reg_date") String reg_date);

}