package com.atguigu.service;

import com.atguigu.entity.Club;

public interface ClubService {
        public Club getUserById(int userId);

        boolean addUser(Club record);
}
