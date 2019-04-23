package com.atguigu.service;

import java.util.Date;

public interface CoachScheduleService {
    public boolean updatelesson(String mem_id, String real_club, String real_coach,
                                String sale_id, Byte seq_no, Date start_time_2,
                                Date end_time_2, String bz1);
}
