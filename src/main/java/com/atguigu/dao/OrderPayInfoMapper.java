package com.atguigu.dao;

import com.atguigu.entity.OrderPayInfo;

public interface OrderPayInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderPayInfo record);

    int insertSelective(OrderPayInfo record);

    OrderPayInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(OrderPayInfo record);

    int updateByPrimaryKey(OrderPayInfo record);
}