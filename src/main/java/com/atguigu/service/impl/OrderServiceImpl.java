package com.atguigu.service.impl;

import com.atguigu.dao.OrderDtlMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;
import com.atguigu.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDtlMapper order;


    public OrderDtl getOrderById(String orderNo) {
        return order.selectByPrimaryKey(orderNo);
    }

    public OrderDtl getOrderByMemId(String memId){
        return order.selectByMemId(memId);
    }
    public boolean addOrder(OrderDtl record){
        boolean result = false;
        try {
            order.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateOrder(OrderDtl record) {
        boolean result = false;
        try {
            order.updateByPrimaryKeySelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}