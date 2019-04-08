package com.atguigu.entity;

public class MemberCourse {
    private String memId;

    private String courseId;

    private Byte totalLesson;

    private Byte used;

    private Byte rem;

    private Byte status;

    private String bz1;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Byte getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(Byte totalLesson) {
        this.totalLesson = totalLesson;
    }

    public Byte getUsed() {
        return used;
    }

    public void setUsed(Byte used) {
        this.used = used;
    }

    public Byte getRem() {
        return rem;
    }

    public void setRem(Byte rem) {
        this.rem = rem;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }
}