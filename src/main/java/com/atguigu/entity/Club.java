package com.atguigu.entity;

import java.math.BigDecimal;

public class Club {
    private String clubId;

    private String name;

    private BigDecimal la;

    private BigDecimal lo;

    private String icon;

    private String address;

    private String tel;

    private String photo;

    private Byte type;

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLa() {
        return la;
    }

    public void setLa(BigDecimal la) {
        this.la = la;
    }

    public BigDecimal getLo() {
        return lo;
    }

    public void setLo(BigDecimal lo) {
        this.lo = lo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}