package com.atguigu.entity;

import java.util.Date;

public class MemberLesson {
    private String memId;

    private String saleId;

    private Byte seqNo;

    private Integer clubId;

    private Byte status;

    private Date startTime1;

    private Date endTime1;

    private Date startTime2;

    private Date endTime2;

    private String coachId;

    private Integer realClub;

    private String realCoach;

    private String bz1;

    private String bz2;

    private Date chTime;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Byte getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Byte seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(Date startTime1) {
        this.startTime1 = startTime1;
    }

    public Date getEndTime1() {
        return endTime1;
    }

    public void setEndTime1(Date endTime1) {
        this.endTime1 = endTime1;
    }

    public Date getStartTime2() {
        return startTime2;
    }

    public void setStartTime2(Date startTime2) {
        this.startTime2 = startTime2;
    }

    public Date getEndTime2() {
        return endTime2;
    }

    public void setEndTime2(Date endTime2) {
        this.endTime2 = endTime2;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public Integer getRealClub() {
        return realClub;
    }

    public void setRealClub(Integer realClub) {
        this.realClub = realClub;
    }

    public String getRealCoach() {
        return realCoach;
    }

    public void setRealCoach(String realCoach) {
        this.realCoach = realCoach;
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

    public Date getChTime() {
        return chTime;
    }

    public void setChTime(Date chTime) {
        this.chTime = chTime;
    }
}