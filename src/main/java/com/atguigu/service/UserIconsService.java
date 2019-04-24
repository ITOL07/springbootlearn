package com.atguigu.service;

import com.atguigu.entity.UserIcons;

import java.util.List;

public interface UserIconsService {

    boolean insertUserIcon(UserIcons icons);

    List<UserIcons> selectByUser(String userid);

    List<UserIcons> selectByType(Integer type);
}
