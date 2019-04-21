package com.atguigu.dao;

import com.atguigu.entity.TPubParam;
import org.apache.ibatis.annotations.Param;

public interface TPubParamMapper {
    int insert(TPubParam record);

    int insertSelective(TPubParam record);

    float selectPct(@Param("cnt") Integer cnt,@Param("paraId") String para_id);
}