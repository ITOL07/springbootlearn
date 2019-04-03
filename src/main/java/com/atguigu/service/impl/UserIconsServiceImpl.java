package com.atguigu.service.impl;

import com.atguigu.dao.UserIconsMapper;
import com.atguigu.entity.UserIcons;
import com.atguigu.service.UserIconsService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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

}
