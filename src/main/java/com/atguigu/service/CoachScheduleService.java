package com.atguigu.service;

import java.util.Date;

public interface CoachScheduleService {

    public boolean updatelesson(String mem_id,String real_club,String real_coach,
                                String sale_id,String kc_id,Integer seq_no,Date start_time_1,
                                Date end_time_1,String bz1);

}
