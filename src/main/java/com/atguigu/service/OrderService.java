package com.atguigu.service;

import com.atguigu.entity.*;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public OrderDtl getOrderById(String orderNo);

    public List<OrderDtl> getOrderByMemId(String memId,String tradeState);

    boolean addOrder(OrderDtl record);

    boolean updateOrder(OrderDtl record);

    public int insertCoachReg(TCoachLessonReg t);

    public int updateCoachReg(TCoachLessonReg t);

    public int insertClubReg(TClubLessonReg t);

    public int updateClubReg(TClubLessonReg t);

    public List<Map<String,String>> getOrderDtlByMemId(String memId, String tradeState);

    public OrderPayInfo getOrderPayInfo(String order_no);

    public int insertOrderPayInfo(OrderPayInfo o);

    public int updateOrderPayInfo(OrderPayInfo o);

}