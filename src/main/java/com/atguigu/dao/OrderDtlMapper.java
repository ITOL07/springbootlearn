package com.atguigu.dao;

import com.atguigu.entity.OrderDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDtlMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderDtl record);

    int insertSelective(OrderDtl record);

    OrderDtl selectByPrimaryKey(String orderNo);
    List<OrderDtl> selectByMemId(@Param("memId") String memId, @Param("tradeState") String tradeState);

    int updateByPrimaryKeySelective(OrderDtl record);

    int updateByPrimaryKey(OrderDtl record);
    int updateByMemId(OrderDtl record);

    List<Map<String,String>> selectWithInfoByMemId(@Param("memId") String memId, @Param("tradeState") String tradeState);
}