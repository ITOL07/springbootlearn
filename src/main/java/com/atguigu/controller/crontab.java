package com.atguigu.controller;

import com.atguigu.service.MemberService;
import com.sun.media.jfxmedia.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Configuration
@EnableScheduling
public class crontab {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(crontab.class);

    @Resource
    private MemberService memberService;
    @Resource
    private AttendClassController attendClassController;
    /**
     * 每隔五分钟执行
     * 对已签到课程进行扫描，对于已经超过签退时间的课程，修改为已完成
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    private void configureTasks() {
        logger.info("执行定时刷新member_lesson表状态: ");
        boolean b = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date=simpleDateFormat.format(new Date());
        logger.info(date);
        List<Map<Object,Object>> list= memberService.getMemberLess("","","","1",date);
        for(Map<Object,Object> map:list){
            logger.info("已签到未签退的课程信息："+map.toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                b = formatter.parse(formatter.format(new Date())).after(formatter.parse(map.get("end_time_1").toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(b){
                logger.info("当前系统时间为："+formatter.format(new Date())+"，当前课程时间"+map.get("end_time_1").toString()+"已过，可以签退～");
                attendClassController.updateCourseInfos(map.get("kc_id").toString(),map.get("seq_no").toString(), "1");
            }else{
                logger.info("当前系统时间为："+formatter.format(new Date())+"，当前课程时间"+map.get("end_time_1").toString()+"还未结束，不进行签退～");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
