package com.atguigu.controller;

import com.atguigu.service.CoachScheduleService;
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
    private static final Logger logger = LoggerFactory.getLogger(CoachScheduleController.class);


    @ResponseBody
    @RequestMapping("/lesson")
    public boolean updatelesson(@RequestParam("mem_id") String mem_id,
                                @RequestParam("real_club") String real_club,
                                @RequestParam("real_coach") String real_coach,
                                @RequestParam("sale_id") String sale_id,
                                @RequestParam("seq_no") Integer seq_no,
                                @RequestParam("start_time_2") String start_time,
                                @RequestParam("end_time_2") String end_time,
                                @RequestParam("bz1") String bz1,
                                @RequestParam("kc_id") String kc_id,
                                HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start_time_2 = new Date(sdf.parse(start_time).getTime());
        Date end_time_2 = new Date(sdf.parse(end_time).getTime());
        logger.info("start_time_2 :"+start_time_2);
        logger.info("传入为："+mem_id);
        Boolean flag1 = scheduleService.updatelesson(mem_id,real_club,real_coach,sale_id,seq_no,start_time_2,end_time_2,bz1,kc_id);
        Boolean falg2 = true;
        return flag1;
    }
}
