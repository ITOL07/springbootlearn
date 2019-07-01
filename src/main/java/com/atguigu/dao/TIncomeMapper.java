package com.atguigu.dao;

import com.atguigu.entity.TIncome;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TIncomeMapper {
    int insert(TIncome record);

    int insertSelective(TIncome record);

    TIncome selectByPrimaryKey(@Param("userId") String userId, @Param("regDate") String regDate);

    int updateSelective(TIncome record);

    Map<String,Object> selectSum(@Param("userId") String coachId, @Param("regDate") String regDate);

}