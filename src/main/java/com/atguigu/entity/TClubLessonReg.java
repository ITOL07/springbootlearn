package com.atguigu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TClubLessonReg {
    private Integer id;

    private String clubId;

    private Integer lesCount;

    private BigDecimal lesPrice;

    private Integer soldCount;

    private BigDecimal soldPrice;

    private Date regDate;

    private Date regTime;

    private Date chTime;

    private String courseType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public Integer getLesCount() {
        return lesCount;
    }

    public void setLesCount(Integer lesCount) {
        this.lesCount = lesCount;
    }

    public BigDecimal getLesPrice() {
        return lesPrice;
    }

    public void setLesPrice(BigDecimal lesPrice) {
        this.lesPrice = lesPrice;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getChTime() {
        return chTime;
    }

    public void setChTime(Date chTime) {
        this.chTime = chTime;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}