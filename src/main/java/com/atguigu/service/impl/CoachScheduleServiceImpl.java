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
                                String sale_id,String kc_id, Byte seq_no, Date start_time_1,
                                Date end_time_1,String bz1) {
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setMemId(mem_id);
        memberLesson.setRealClub(real_club);
        memberLesson.setRealCoach(real_coach);
        memberLesson.setSaleId(sale_id);
        memberLesson.setKcId(kc_id);
        memberLesson.setSeqNo(seq_no);
        memberLesson.setStartTime1(start_time_1);
        memberLesson.setEndTime1(end_time_1);

        Boolean flag = memberLessonMapper.updatelesson(memberLesson);
        return flag;
    }
}
