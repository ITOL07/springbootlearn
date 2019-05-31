package com.atguigu.service.impl;

import com.atguigu.dao.*;
import com.atguigu.entity.*;
import com.atguigu.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDtlMapper order;

    @Resource
    private TCoachLessonRegMapper tCoachLessonRegMapper;

    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;

    @Resource
    private OrderPayInfoMapper orderPayInfoMapper;


    public OrderDtl getOrderById(String orderNo) {
        return order.selectByPrimaryKey(orderNo);
    }

    public List<OrderDtl> getOrderByMemId(String memId,String trade_state){
        return order.selectByMemId(memId,trade_state);
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

    //插入教练日记表
    public int insertCoachReg(TCoachLessonReg t){
        return tCoachLessonRegMapper.insertSelective(t);
    }

    public int updateCoachReg(TCoachLessonReg t){
        return tCoachLessonRegMapper.updateByPrimaryKeySelective(t);
    }

    //插入场地日记表
    public int insertClubReg(TClubLessonReg t){
        return tClubLessonRegMapper.insertSelective(t);
    }

    public int updateClubReg(TClubLessonReg t){
        return tClubLessonRegMapper.updateByPrimaryKeySelective(t);
    }

    public List<Map<String,String>> getOrderDtlByMemId(String memId, String tradeState){
        return order.selectWithInfoByMemId(memId,tradeState);
    }

    @Override
    public OrderPayInfo getOrderPayInfo(String order_no) {
        return orderPayInfoMapper.selectByPrimaryKey(order_no);
    }
    @Override
    public int insertOrderPayInfo(OrderPayInfo o) {
        return orderPayInfoMapper.insertSelective(o);
    }
    @Override
    public int updateOrderPayInfo(OrderPayInfo o) {
        return orderPayInfoMapper.updateByPrimaryKeySelective(o);
    }
}