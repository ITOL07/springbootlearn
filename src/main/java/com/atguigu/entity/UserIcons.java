package com.atguigu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserIcons {
    private String iconName;

    private String userId;

    private String iconUrl;

    private Integer type;

    private Date uptime;

    private String bz1;

    private String bz2;

//    public String getIconName() {
//        return iconName;
//    }
//
//    public void setIconName(String iconName) {
//        this.iconName = iconName;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getIconUrl() {
//        return iconUrl;
//    }
//
//    public void setIconUrl(String iconUrl) {
//        this.iconUrl = iconUrl;
//    }
}