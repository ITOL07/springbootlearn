package com.atguigu.entity;

import java.util.Date;

public class User {
    private String id;

    private String userName;

    private String password;

    private Byte chn;

    private Date lastLogin;

    private String openId;

    private String try_flag;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getChn() {
        return chn;
    }

    public void setChn(Byte chn) {
        this.chn = chn;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getTry_flag() {
        return try_flag;
    }

    public void setTry_flag(String try_flag) {
        this.try_flag = try_flag;
    }


}