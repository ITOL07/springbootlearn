package com.atguigu.dao;

import com.atguigu.entity.CoachLesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CoachLessonMapper {
    int insert(CoachLesson record);

    int insertSelective(CoachLesson record);

    List<Map<Object, Object>>  selectByView(@Param("coach_id") String coach_id, @Param("status") Integer status);

    List<Map<Object, Object>>  selectByView_id(@Param("coach_id") String coach_id);
}