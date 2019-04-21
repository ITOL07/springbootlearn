package com.atguigu.dao;

import com.atguigu.entity.Club;
import org.apache.ibatis.annotations.Param;

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

    List<Map<Object,Object>> selectLessByView(@Param("clubId") String clubId, @Param("startTime") String startTime,@Param("status") Integer status);
    List<Map<Object,Object>> selectByView(@Param("clubId") String clubId, @Param("status") Integer status);
    List<Map<Object,Object>> selectByView_id(@Param("clubId") String clubId,@Param("startTime") String startTime);
}