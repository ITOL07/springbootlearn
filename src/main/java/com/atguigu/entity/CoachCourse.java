package com.atguigu.entity;

public class CoachCourse {
    private String coachId;

    private String courseId;

    private Byte totalLesson;

    private Byte used;

    private Byte rem;

    private String clubId;

    private Byte status;

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
}