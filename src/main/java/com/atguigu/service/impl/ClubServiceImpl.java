package com.atguigu.service.impl;

import com.atguigu.dao.ClubMapper;
import com.atguigu.entity.Club;
import com.atguigu.entity.User;
import com.atguigu.service.ClubService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubMapper clubMapper;


    public Club getClubById(String userId) {
        return clubMapper.selectByPrimaryKey(userId);
    }

    public boolean addUser(Club record){
        boolean result = false;
        try {
            clubMapper.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}