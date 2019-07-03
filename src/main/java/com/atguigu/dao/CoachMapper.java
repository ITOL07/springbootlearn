package com.atguigu.dao;

import com.atguigu.entity.Coach;

import java.util.List;
import java.util.Map;

public interface CoachMapper {
    int deleteByPrimaryKey(String coachId);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(String coachId);

    List<Coach> selectByClubId(String coachId);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);

    List<Map<Object,Object>> selectMyMemId(String coachId);

    Coach getCoachInfo(String coach_id);
    Map<String,Object> getCoachInfoByView(String coach_id);

    //新增selectCoach方法，入参为实体类，参数可变化
    Coach selectCoach(Coach coach);
}