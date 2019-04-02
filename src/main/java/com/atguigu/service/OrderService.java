package com.atguigu.service;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;

public interface OrderService {
    public OrderDtl getUserById(String orderNo);

    boolean addOrder(OrderDtl record);

}