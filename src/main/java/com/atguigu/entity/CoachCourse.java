package com.atguigu.entity;

public class CoachCourse {
    private Integer coachId;

    private Byte status;

    private Byte totalLesson;

    private Byte used;

    private Byte rem;

    private Integer courseId;

    private Integer clubId;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
}