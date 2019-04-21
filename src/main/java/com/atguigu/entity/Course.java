package com.atguigu.entity;

import java.math.BigDecimal;

public class Course {
    private String courseId;

    private Integer type;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private Byte status;

    private String coachId;

    private Byte coachType;

    private String clubId;

    private Byte clubType;

    private String try_flag;

    private String bz1;

    private String bz2;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public Byte getCoachType() {
        return coachType;
    }

    public void setCoachType(Byte coachType) {
        this.coachType = coachType;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public Byte getClubType() {
        return clubType;
    }

    public void setClubType(Byte clubType) {
        this.clubType = clubType;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getTry_flag() {
        return try_flag;
    }

    public void setTry_flag(String try_flag) {
        this.try_flag = try_flag;
    }
}