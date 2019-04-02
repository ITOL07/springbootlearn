package com.atguigu.dao;

import com.atguigu.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}