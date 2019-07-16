package com.atguigu.controller;

import com.atguigu.entity.MemberLesson;
import com.atguigu.service.CoachScheduleService;
import com.atguigu.service.MemberService;
import com.atguigu.util.CommonRpc;
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
import java.util.*;

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
//                                @RequestParam("sale_id") String sale_id,
                                @RequestParam("kc_id") String kc_id,
                                @RequestParam("seq_no") Byte seq_no,
                                @RequestParam("start_time_1") String start_time,
//                                @RequestParam("end_time_1") String end_time,
                                @RequestParam("bz1") String bz1,
                                HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start_time_1 = new Date(sdf.parse(start_time).getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start_time_1);
        calendar.add(Calendar.HOUR, 1);
        Date end_time_1 = calendar.getTime();

        logger.info("start_time_1 :"+start_time_1);
//        logger.info("传入为：mem_id:"+mem_id+",sale_id:"+sale_id+",kc_id:"+kc_id);
        logger.info("传入为：mem_id:"+mem_id+",kc_id:"+kc_id);

        //优先查询表中条件为mem_id、sale_id、（start_time_2、end_time_2为空）条件下最小的seq_no返回并赋值
               // Byte sss = scheduleService.selectseqno(mem_id,sale_id);
        MemberLesson memberLesson = new MemberLesson();
        memberLesson.setMemId(mem_id)
//                .setSaleId(sale_id)
                .setKcId(kc_id);
        int selectseqno = memberService.selectseqno(memberLesson);
        logger.info("查询当前课程节数号为："+selectseqno);
        Boolean flag1 = scheduleService.updatelesson(mem_id,real_club,real_coach,null,kc_id,selectseqno,start_time_1,end_time_1,bz1);
        Boolean falg2 = true;
        return flag1;
    }

    /**
     *
     * @param mem_id
     * @param date
     * @param times
     * @return
     */
//    @ResponseBody
//    @RequestMapping("/listTimes")
//    public List<String> getListTimes(@RequestParam("mem_id") String mem_id, @RequestParam("date") String date, @RequestParam("times") List<String> times){
//        logger.info("listTimes 传入参数 mem_id："+mem_id+",date："+date.substring(0,10));
//        logger.info("=================");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        int i1 = Integer.parseInt(simpleDateFormat.format(new Date()).substring(11, 13));
//        //保证传入日期格式
//        String date1 = date.substring(0,10);
//        List<String> listTimes = memberService.getListTimes(mem_id, date1);
//        for(int i=0;i<times.size();i++){
//            logger.info("被删除前，此时当前位置时间为："+times.get(i));
//            int i2 = Integer.parseInt(times.get(i).replaceAll(" ", "").split("-")[1].split(":")[0]);
//            if(14>i2){
//                logger.info("小于当前时间，被删除");
//                times.remove(i);
//                i--;
//                continue;
//            }
//            String xxx = times.get(i).replaceAll(" ","").split("-")[1];
//            boolean flag = false;
//            for(String listTimes1:listTimes){
//                logger.info("已被预定时间："+listTimes1+"这个时间是否能被删除："+(listTimes1.endsWith(xxx)||listTimes1.equals(xxx)));
//                if(listTimes1.endsWith(xxx)||listTimes1.equals(xxx)){
//                    times.remove(i);
//                    flag= true;
//                    logger.info("被删除后，此时当前索引值为："+i);
//                    break;
//                }
//            }
//            if(flag){
//                i--;
//            }
//        }
//        return times;
//    }
//}

/**
 *
 * @param mem_id
 * @param date
 * @param times
 * @return
 */
//    @ResponseBody
//    @RequestMapping("/listTimes")
//    public List<String> getListTimes(@RequestParam("mem_id") String mem_id, @RequestParam("date") String date, @RequestParam("times") List<String> times){
//        logger.info("listTimes 传入参数 mem_id："+mem_id+",date："+date.substring(0,10));
//        logger.info("=================");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat simpleDateFormatx = new SimpleDateFormat("yyyy-MM-dd");
//        int i1 = Integer.parseInt(simpleDateFormat.format(new Date()).substring(11, 13));
//        //保证传入日期格式
//        String date1 = date.substring(0,10);
//        boolean b = false;
//        try {
//            b = simpleDateFormatx.parse(simpleDateFormatx.format(new Date())).before(simpleDateFormatx.parse(date1));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        List<String> listTimes = memberService.getListTimes(mem_id, date1);
//        for(int i=0;i<times.size();i++){
//            logger.info("被删除前，此时当前位置时间为："+times.get(i));
//            int i2 = Integer.parseInt(times.get(i).replaceAll(" ", "").split("-")[1].split(":")[0]);
//            if(b){
//                logger.info("选择日期大于当前系统日期");
//            }else{
//                if(i1>=i2){
//                    logger.info("小于当前时间，被删除");
//                    times.remove(i);
//                    i--;
//                    continue;
//                }
//            }
////            if(!b&&i1>i2){
////                logger.info("小于当前时间，被删除");
////                times.remove(i);
////                i--;
////                continue;
////            }
//            String xxx = times.get(i).replaceAll(" ","").split("-")[1];
//            boolean flag = false;
//            for(String listTimes1:listTimes){
//                logger.info("已被预定时间："+listTimes1+"这个时间是否能被删除："+(listTimes1.endsWith(xxx)||listTimes1.equals(xxx)));
//                if(listTimes1.endsWith(xxx)||listTimes1.equals(xxx)){
//                    times.remove(i);
//                    flag= true;
//                    logger.info("被删除后，此时当前索引值为："+i);
//                    break;
//                }
//            }
//            if(flag){
//                i--;
//            }
//        }
//        return times;
//    }

    @ResponseBody
    @RequestMapping("/getUsedTime")
    public List<Map<Object,Object>> getUsedTimes (
            @RequestParam("mem_id") String mem_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("date") String date){

//        List<Map<Object,Object>> list = new
        logger.info("getUsedTime : mem_id+++"+mem_id+"=== coach_id+++"+coach_id+"  date==="+date);
        return memberService.getUsedTimes(mem_id,coach_id,date);

    }

    @ResponseBody
    @RequestMapping("/listTimes")
    public Map<String,String>  getListTimes(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("coach_id") String coach_id,
            @RequestParam("start_time") String start_time
    ){
        //默认所选时间可用
        boolean bool = true;


        SimpleDateFormat formatter_in = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reg_date=start_time.substring(0,10);
        logger.info("listTimes 传入参数 mem_id："+mem_id+",reg_date："+reg_date);
        logger.info("=================");

        Date  start_time_in=null;
        Date  start_time_db,end_time_db=null;
        try {
            start_time_in =formatter_in.parse(start_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start_time_in);

        calendar.add(Calendar.MINUTE,1);
        start_time_in=calendar.getTime();

        calendar.add(Calendar.HOUR, 1);
        Date end_time_in = calendar.getTime();

        logger.info("传入的时间为： start_time=="+start_time_in+"===end_time=="+end_time_in);

        //获取已经用过的时间段列表
        List<Map<Object,Object>> usedList=getUsedTimes(mem_id,coach_id,reg_date);

        for(Map<Object,Object> map :usedList) {

            try{
                start_time_db=formatter.parse(map.get("start_time_1").toString());
                end_time_db=formatter.parse(map.get("end_time_1").toString());
                logger.info("数据库的时间为： start_time=="+start_time_db+"===end_time=="+end_time_db);

                boolean b1=CommonRpc.isEffectiveDate(start_time_in,start_time_db,end_time_db);
                boolean b2=CommonRpc.isEffectiveDate(end_time_in,start_time_db,end_time_db);
                if(b1||b2){
                    bool=false;
                    logger.info("传入时间段内已经有课了哦！！");
                    break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



        Map<String,String> resMap = new HashMap<>();
        if(!bool){

            resMap.put("res_code","-1");
            resMap.put("res_info","所选时间段已经有课了呢！");
        }else{
            resMap.put("res_code","0");
            resMap.put("res_info","所选时间段还没有课，可以排课！");
        }
        return resMap;
    }

}
