package com.atguigu.service;

import com.atguigu.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService1 {
    public User getUserById(int userId);

    boolean addUser(User record);

}