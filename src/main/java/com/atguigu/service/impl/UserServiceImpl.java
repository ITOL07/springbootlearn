package com.atguigu.service.impl;


import com.atguigu.dao.UserMapper;
import com.atguigu.entity.User;
import com.atguigu.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService1 {

    @Resource
    private UserMapper userDao;

    public User getUserById(String userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public User getUserByOpenId(String openId) {
        return userDao.selectByOpenid(openId);
    }

    public Map<Object,Object> getUserByName(String userId) {
        return userDao.selectbyUsername(userId);
    }

    public String getMaxId(Integer type){ return userDao.selectMaxId(type); }
    public boolean addUser(User record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateUser(User record){
        boolean result = false;
        try {
            userDao.updateByPrimaryKey(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public boolean updateUser1(User record){
        boolean result = false;
        try {
            userDao.updateByUserName(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean updateUserByOpenid(User record){
        boolean result = false;
        try {
            userDao.updateByOpenId(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public User getUser(User user){
        return userDao.selectUser(user);
    }
}