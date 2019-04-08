package com.atguigu.entity;

import java.util.Date;

public class CoachLesson {
    private String coachId;

    private String courseId;

    private Byte seqNo;

    private String clubId;

    private Byte status;

    private Date startTime1;

    private Date endTime1;

    private Date startTime2;

    private Date endTime2;

    private String memId;

    private String bz1;

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Byte getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Byte seqNo) {
        this.seqNo = seqNo;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
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

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }
}