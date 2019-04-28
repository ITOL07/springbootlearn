package com.atguigu.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.*;
import com.atguigu.service.ClubService;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@RestController
//@RequestMapping("/mydb")
@RequestMapping("/club")
public class ClubController {

    private Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private ClubService clubService;
    @Resource
    private CourseService courseService;
    @Resource
    private CoachService coachService;

    @RequestMapping("/getClub")
//    @RequestMapping("/qry")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from club";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
//                    logger.info(key + ":" + value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/showClub")
    @ResponseBody
    public Club getUserById(HttpServletRequest request, Model model) {
        String userId = request.getParameter("id");
        logger.info("id=====" + userId);
        Club club = this.clubService.getClubById(userId);
        return club;
    }

    @RequestMapping("/qry")
    public Club qryClub(
            @RequestParam("club_id") String club_id
    ) {
        Club c = new Club();

        Map<String, String> param = new HashMap<>();
        JSONObject result = new JSONObject();

        logger.info("club_id ====" + club_id);
        c = clubService.getClubById(club_id);

        return c;
    }

    @RequestMapping("/update")
    public boolean addClub(
            @RequestParam("club_id") String club_id,
            @RequestParam("name") String name,
            @RequestParam("la") BigDecimal la,
            @RequestParam("lo") BigDecimal lo,
            @RequestParam("addr") String addr,
            @RequestParam("tel") String tel,
            @RequestParam("area") String area,
            @RequestParam("open_time") String open_time,
            @RequestParam("close_time") String close_time,
            @RequestParam("jcss") String jcss
    ) {
        Club c = new Club();
        c.setClubId(club_id);
        c.setName(name);
        c.setLa(la);
        c.setLo(lo);
        c.setAddress(addr);
        c.setTel(tel);
        c.setArea(Float.parseFloat(area));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:SS");
        Date time1 = null;
        Date time2 = null;
        try {
            time1 = sdf.parse(open_time);
            time2 = sdf.parse(close_time);

        } catch (ParseException e) {
            logger.info("抛出异常");
        }
        c.setOpenTime(time1);
        c.setCloseTime(time2);
        c.setJcss(jcss);

//        String s = clubService.getMaxId();
//        int i = Integer.parseInt(s);
//        c.setClubId(String.valueOf(++i));

        logger.info("club_id ====" + club_id);
        return clubService.updateClub(c);

    }

    @RequestMapping("/qryCourse")
    public List<Course> qryClubCourse(
            @RequestParam("club_id") String club_id,
            @RequestParam("bz1") String bz1
    ) {
        List<Course> list = courseService.getCourseByClubId(club_id, bz1);
        logger.info("club_id ====" + club_id);

        return list;
    }

    @RequestMapping("/qryCoach")
    public List<Coach> qryCoachCourse(
            @RequestParam("club_id") String club_id
    ) {
        List<Coach> list = coachService.getCoachByClubId(club_id);
        logger.info("club_id ====" + club_id);

        return list;
    }

    @RequestMapping("/qryLesson")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("club_id") String club_id,
            @RequestParam("status") Integer status,
            @RequestParam("start_time") String startTime
    )
    {
        logger.info("mem_id ====" + club_id + "status=====" + status);
//        List<Map<Object,Object>> list= new ArrayList<Map<Object,Object>>();
//        List<Map<Object,Object>> list_res= new ArrayList<Map<Object,Object>>();
//        list=memberService.getMemberLessByIdS(mem_id,status);

//        for(Map<Object,Object> map:list){
//            String sale_id=map.get("sale_id").toString();
//            logger.info("sale_id====="+sale_id);
//            Course course  = courseService.getCourseById(sale_id);
//            map.put("")
//        }
//        return memberService.getMemberLessByIdS(mem_id,status);
        if (status != null) {
            return clubService.getClubLessByViewTime(club_id, startTime,status);
        } else {
            return clubService.getClubLessByView_id(club_id,startTime);
        }
    }

    @RequestMapping("/qryLessReg")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("club_id") String club_id,
            @RequestParam("reg_date") String reg_date
    ) {
        logger.info("club_id ====" + club_id + "reg_date=====" + reg_date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            logger.info("抛出异常");
        }
        return clubService.getClubIncomeById(club_id, date);

    }

    @RequestMapping("/qrySum")
    public Map<Object, Object> qrySum(
            @RequestParam("club_id") String club_id,
            @RequestParam("reg_date") String reg_date
    ) {
        logger.info("club_id ====" + club_id + "reg_date=====" + reg_date);

        Map<Object,Object> map= new HashMap<Object,Object>();
        Map<Object,Object> resMap= new HashMap<Object,Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            logger.info("抛出异常");
        }

        map=clubService.getClubIncomeSumById(club_id, date);
        logger.info("map.get(\"les_count_sum\");==========="+map.get("les_count_sum"));

        int les_count_sum=Integer.parseInt(map.get("les_count_sum").toString());
        int sold_count_sum=Integer.parseInt(map.get("les_count_sum").toString());

        float les_total_amt=Float.parseFloat(map.get("les_total_amt").toString());
        float sold_total_amt=Float.parseFloat(map.get("sold_total_amt").toString());

        float les_pct=0.10f;
        float sold_pct=0.10f;

        float les_amt=les_total_amt*les_pct;
        float sold_amt=sold_total_amt*sold_pct;

        resMap.put("les_total_amt",les_amt);
        resMap.put("sold_total_amt",sold_amt);

        logger.info("les_count_sum=["+les_count_sum+"]\t les_pct=["+les_pct+"]\t les_amt=["+les_amt+"]\t les_total_amt=["+les_total_amt+"]");
        logger.info("sold_count_sum=["+sold_count_sum+"]\t sold_pct=["+sold_pct+"]\t sold_amt=["+sold_amt+"]\t sold_total_amt=["+sold_total_amt+"]");
        return resMap;

    }

    @RequestMapping("/getCourseInfo")
    public CourseInfo getCourseInfo(@RequestParam("type") String course_type){
        return coachService.getCourseInfo(course_type);
    }

    @RequestMapping("/getCourseType")
    public List<CourseInfo> getCourseType(@RequestParam("club_id") String club_id,@RequestParam("bz1") String bz1){
        List<Map<String,String>> list = new ArrayList<>();
        List<CourseInfo> res_list = new ArrayList<>();
        list = coachService.getCourseTypeByClubId(club_id,bz1);
        for(int i =0;i<list.size();i++){
            CourseInfo c = new CourseInfo();
            c=coachService.getCourseInfo(list.get(i).get("type"));
            res_list.add(c);
        }
        return res_list;
    }
}