package com.atguigu.service.impl;

import com.atguigu.dao.UserIconsMapper;
import com.atguigu.entity.UserIcons;
import com.atguigu.service.UserIconsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("userIconsService")
public class UserIconsServiceImpl implements UserIconsService {


    @Resource
    private UserIconsMapper userIconsMapper;

    @Override
    public boolean insertUserIcon(UserIcons icons) {
        boolean result = false;
        try {
            userIconsMapper.insert(icons);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UserIcons> selectByUser(String userid) {

        List<UserIcons> userIconsList = userIconsMapper.selectByUser(userid);
        return userIconsList;
    }
    @Override
    public List<Map<String, String>> queryCoachInfoIcons(String coach_id, Integer type) {
        return userIconsMapper.queryCoachInfoIcons(coach_id,type);
    }
    @Override
    public List<UserIcons> selectByType(Integer type) {
        List<UserIcons> userIconsList = userIconsMapper.selectByType(type);
        return userIconsList;
    }

    @Override
    public List<UserIcons> selectByIdType(String user_id,Integer type){
        List<UserIcons> userIconsList = userIconsMapper.selectByIdType(user_id,type);
        return userIconsList;
    }
}
