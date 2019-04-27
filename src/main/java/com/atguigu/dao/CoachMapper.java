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
}