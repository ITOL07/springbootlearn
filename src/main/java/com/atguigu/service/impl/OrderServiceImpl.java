package com.atguigu.service.impl;

import com.atguigu.dao.OrderDtlMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.dao.TClubLessonRegMapper;
import com.atguigu.dao.TCoachLessonRegMapper;
import com.atguigu.entity.Order;
import com.atguigu.entity.OrderDtl;
import com.atguigu.entity.TClubLessonReg;
import com.atguigu.entity.TCoachLessonReg;
import com.atguigu.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDtlMapper order;

    @Resource
    private TCoachLessonRegMapper tCoachLessonRegMapper;

    @Resource
    private TClubLessonRegMapper tClubLessonRegMapper;


    public OrderDtl getOrderById(String orderNo) {
        return order.selectByPrimaryKey(orderNo);
    }

    public List<OrderDtl> getOrderByMemId(String memId){
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

}