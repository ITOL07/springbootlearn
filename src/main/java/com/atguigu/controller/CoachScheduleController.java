package com.atguigu.controller;

import com.atguigu.entity.MemberLesson;
import com.atguigu.service.CoachScheduleService;
import com.atguigu.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/schedule")
public class CoachScheduleController {

    @Resource
    private CoachScheduleService scheduleService;
    @Resource
    private MemberService memberService;
    private static final Logger logger = LoggerFactory.getLogger(CoachScheduleController.class);


    @ResponseBody
    @RequestMapping("/lesson")
    public boolean updatelesson(@RequestParam("mem_id") String mem_id,
                                @RequestParam("real_club") String real_club,
                                @RequestParam("real_coach") String real_coach,
                                @RequestParam("sale_id") String sale_id,
                                @RequestParam("kc_id") String kc_id,
                                @RequestParam("seq_no") Byte seq_no,
                                @RequestParam("start_time_1") String start_time,
                                @RequestParam("end_time_1") String end_time,
                                @RequestParam("bz1") String bz1,
                                HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start_time_1 = new Date(sdf.parse(start_time).getTime());
        Date end_time_1 = new Date(sdf.parse(end_time).getTime());
        logger.info("start_time_1 :"+start_time_1);
        logger.info("传入为：mem_id:"+mem_id+",sale_id:"+sale_id+",kc_id:"+kc_id);


        //优先查询表中条件为mem_id、sale_id、（start_time_2、end_time_2为空）条件下最小的seq_no返回并赋值
               // Byte sss = scheduleService.selectseqno(mem_id,sale_id);
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setMemId(mem_id).setSaleId(sale_id).setKcId(kc_id);
        byte selectseqno = memberService.selectseqno(memberLesson);
        logger.info("查询当前课程节数号为："+selectseqno);
        Boolean flag1 = scheduleService.updatelesson(mem_id,real_club,real_coach,sale_id,kc_id,selectseqno,start_time_1,end_time_1,bz1);
        Boolean falg2 = true;
        return flag1;
    }
}
