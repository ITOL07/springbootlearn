package com.atguigu.service.impl;

import com.atguigu.dao.MemberLessonMapper;
import com.atguigu.entity.MemberLesson;
import com.atguigu.service.CoachScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("CoachScheduleService")
public class CoachScheduleServiceImpl implements CoachScheduleService {
    @Resource
    MemberLessonMapper memberLessonMapper;


    @Override
    public boolean updatelesson(String mem_id, String real_club, String real_coach,
                                String sale_id,String kc_id, Integer seq_no, Date start_time_1,
                                Date end_time_1,String bz1) {
        byte status = 0;
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setMemId(mem_id)
                .setRealClub(real_club)
                .setRealCoach(real_coach)
                .setSaleId(sale_id)
                .setKcId(kc_id)
                .setSeqNo(seq_no)
                .setStartTime1(start_time_1)
                .setEndTime1(end_time_1)
                .setStatus(status)
                .setBz1(bz1);


        Boolean flag = memberLessonMapper.updatelesson(memberLesson);
        return flag;
    }
}
