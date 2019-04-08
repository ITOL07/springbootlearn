package com.atguigu.dao;

import com.atguigu.entity.OrderDtl;

import java.util.List;

public interface OrderDtlMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderDtl record);

    int insertSelective(OrderDtl record);

    OrderDtl selectByPrimaryKey(String orderNo);
    List<OrderDtl> selectByMemId(String memId);

    int updateByPrimaryKeySelective(OrderDtl record);

    int updateByPrimaryKey(OrderDtl record);
    int updateByMemId(OrderDtl record);
}