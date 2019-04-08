package com.atguigu.service;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;

import java.util.List;

public interface OrderService {
    public OrderDtl getOrderById(String orderNo);

    public List<OrderDtl> getOrderByMemId(String memId);

    boolean addOrder(OrderDtl record);

    boolean updateOrder(OrderDtl record);

}