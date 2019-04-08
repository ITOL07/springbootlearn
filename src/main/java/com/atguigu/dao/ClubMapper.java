package com.atguigu.dao;

import com.atguigu.entity.Club;

import java.util.List;
import java.util.Map;

public interface ClubMapper {
    int deleteByPrimaryKey(String clubId);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(String clubId);

    List<Map<Object,Object>> selectCoachById(String clubId);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);

    String selectMaxId();
}