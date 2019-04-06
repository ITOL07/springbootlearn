package com.atguigu.service;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;

public interface OrderService {
    public OrderDtl getOrderById(String orderNo);

    public OrderDtl getOrderByMemId(String memId);

    boolean addOrder(OrderDtl record);

    boolean updateOrder(OrderDtl record);

}