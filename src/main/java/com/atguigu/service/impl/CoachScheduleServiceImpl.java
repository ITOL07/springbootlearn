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
                                String sale_id, Integer seq_no, Date start_time_2,
                                Date end_time_2,String bz1) {
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setMemId(mem_id);
        memberLesson.setRealClub(real_club);
        memberLesson.setRealCoach(real_coach);
        memberLesson.setSaleId(sale_id);
        memberLesson.setSeqNo(seq_no);
        memberLesson.setStartTime2(start_time_2);
        memberLesson.setEndTime2(end_time_2);
        Boolean flag = memberLessonMapper.updatelesson(memberLesson);
        return flag;
    }
}
