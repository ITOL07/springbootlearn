package com.atguigu.entity;

import java.math.BigDecimal;

public class OrderDtl {
    private String orderNo;

    private String memId;

    private Integer courseId;

    private BigDecimal amount;

    private Integer count;

    private Byte tradeState;

    private String des;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getTradeState() {
        return tradeState;
    }

    public void setTradeState(Byte tradeState) {
        this.tradeState = tradeState;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}