package com.atguigu.service;

import com.atguigu.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService1 {
    public User getUserById(String userId);
    public User getUserByOpenId(String openId);
    public Map<Object,Object> getUserByName(String userId);
    public String getMaxId(Integer type);

    boolean addUser(User record);

    boolean updateUser(User user);
    boolean updateUser1(User user);
    boolean updateUserByOpenid(User record);
    public User getUser(User user);
}