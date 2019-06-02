package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class UserIcons {
    private String iconName;

    private String userId;

    private String iconUrl;

    private Integer type;

    private Date up_time;

    private String bz1;

    private String bz2;

}