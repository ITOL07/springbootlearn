package com.atguigu.service;

import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;
import com.atguigu.entity.TClubLessonReg;
import com.atguigu.entity.TCoachLessonReg;

import java.util.List;

public interface OrderService {
    public OrderDtl getOrderById(String orderNo);

    public List<OrderDtl> getOrderByMemId(String memId);

    boolean addOrder(OrderDtl record);

    boolean updateOrder(OrderDtl record);

    public int insertCoachReg(TCoachLessonReg t);

    public int updateCoachReg(TCoachLessonReg t);

    public int insertClubReg(TClubLessonReg t);

    public int updateClubReg(TClubLessonReg t);

}