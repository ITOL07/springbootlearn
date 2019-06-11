package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class User {
    private String id;

    private String userName;

    private String password;

    private Byte chn;

    private Date lastLogin;

    private String openId;

    private String try_flag;

    private Integer type;
}