package com.atguigu.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.*;
import com.atguigu.service.ClubService;
import com.atguigu.service.CoachService;
import com.atguigu.service.CourseService;
import com.atguigu.service.MemberService;
import com.atguigu.util.CommParams;
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
    @Resource
    private MemberService memberService;

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
                    if(key.equals("icon")){
                        String tmp=CommParams.WEB_URL+entry.getValue().toString().replaceAll("/app/test","");
                        entry.setValue(tmp);
                    }
                }
            }
        }
        return list;
    }

    @RequestMapping("/getClubByCoachId")
//    @RequestMapping("/qry")
    public List<Map<String, Object>> getClubByCoachId(@RequestParam("coach_id") String coach_id) {
        String sql = "select distinct a.* from club a,member_lesson b where a.club_id=b.club_id and b.coach_id='"+coach_id+"'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if(key.equals("icon")){
                        String tmp=CommParams.WEB_URL+entry.getValue().toString().replaceAll("/app/test","");
                        entry.setValue(tmp);
                    }

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
        String tmp=CommParams.WEB_URL+c.getIcon().replaceAll("/app/test","");
        c.setIcon(tmp);

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

    @RequestMapping("/qryCoach_bak")
    public List<Coach> qryCoachCourse(
            @RequestParam("club_id") String club_id
    ) {
        List<Coach> resList=new ArrayList<>();
        List<Coach> list = coachService.getCoachByClubId(club_id);
        logger.info("club_id ====" + club_id);
        for(Coach c:list){
            String tmp= CommParams.WEB_URL+c.getIcon().replaceAll("/app/test","");
            c.setIcon(tmp);
            resList.add(c);
        }

        return resList;
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
        List<Map<Object, Object>> listInfo = new ArrayList<>();
        if (status != null) {
            listInfo= clubService.getClubLessByViewTime(club_id, startTime,status);
        } else {
            listInfo= clubService.getClubLessByView_id(club_id,startTime);
        }
        for(Map<Object, Object> map:listInfo){
            map.put("datex",String.valueOf(map.get("start_time_1")).split(" ")[0]);
            map.put("timex",String.valueOf(map.get("start_time_1")).split(" ")[1]+"-"+String.valueOf(map.get("start_time_1")).split(" ")[1]);
        }
        return listInfo;
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

        resMap.put("les_total_cnt",les_count_sum);
        resMap.put("sold_total_cnt",sold_count_sum);
        resMap.put("les_total_amt",les_amt);
        resMap.put("sold_total_amt",sold_amt);

        logger.info("les_count_sum=["+les_count_sum+"]\t les_pct=["+les_pct+"]\t les_amt=["+les_amt+"]\t les_total_amt=["+les_total_amt+"]");
        logger.info("sold_count_sum=["+sold_count_sum+"]\t sold_pct=["+sold_pct+"]\t sold_amt=["+sold_amt+"]\t sold_total_amt=["+sold_total_amt+"]");
        return resMap;

    }

    @RequestMapping("/getCourseInfo")
    public Map<String,String> getCourseInfo(@RequestParam("type") String course_type,@RequestParam("id") String id){
        Map<String,String> resMap=new HashMap<>();

        logger.info("type==="+course_type+"club_id or coach_id====" + id);
        CourseInfo cInfo= new CourseInfo();
        cInfo=coachService.getCourseInfo(course_type);
        String tmp=CommParams.WEB_URL+cInfo.getBz2().replaceAll("/app/test","");
        cInfo.setBz2(tmp);
        tmp=CommParams.WEB_URL+cInfo.getInfo_pic().replaceAll("/app/test","");
        cInfo.setInfo_pic(tmp);
        tmp=CommParams.WEB_URL+cInfo.getSale_pic().replaceAll("/app/test","");
        cInfo.setSale_pic(tmp);


        Map <String,String> map=courseService.getCoursePrice(id,course_type);
        System.out.println(map);

        resMap.put("courseName",cInfo.getCourseName());
        resMap.put("courseType",cInfo.getCourseType());
        resMap.put("tryFlag",cInfo.getTryFlag());
        resMap.put("bz2",cInfo.getBz2());
        resMap.put("info_pic",cInfo.getInfo_pic());
        resMap.put("sale_pic",cInfo.getSale_pic());
        resMap.put("brief",cInfo.getBrief());
        resMap.put("detail", cInfo.getDetail());
        resMap.put("approp",cInfo.getApprop());
        resMap.put("courseTime",cInfo.getCourseTime());
        resMap.put("suggest",cInfo.getSuggest());
        resMap.put("min_count",cInfo.getMin_count()+"");
        Object ob = map.get("min_price");
        resMap.put("min_price",ob.toString());
        resMap.put("max_price",((Object)map.get("max_price")).toString());
        resMap.put("process",cInfo.getProcess());
        return resMap;
    }

    @RequestMapping("/getCourseType")
    public List<Map<String,String>> getCourseType(@RequestParam("club_id") String club_id,@RequestParam("bz1") String bz1){
        List<Map<String,String>> list = new ArrayList<>();
        List<Map<String,String>> res_list = new ArrayList<>();
        list = coachService.getCourseTypeByClubId(club_id,bz1);
        for(int i =0;i<list.size();i++){
            Map<String,String> map= new HashMap<>();
            CourseInfo c = new CourseInfo();
            c=coachService.getCourseInfo(list.get(i).get("type"));
            String tmp=CommParams.WEB_URL+c.getBz2().replaceAll("/app/test","");
            c.setBz2(tmp);
            map.put("courseType",c.getCourseType());
            map.put("courseName",c.getCourseName());
            map.put("brief",c.getBrief());
            map.put("bz2",c.getBz2());
            Object ob =list.get(i).get("min_price");
            System.out.println(ob.toString());
            map.put("min_price",ob.toString());

            res_list.add(map);
        }
        return res_list;
    }

    @RequestMapping("/qryCoach")
    public List<Map<String,String>> qryCoachCourse_new(
            @RequestParam("club_id") String club_id
    ) {
        List<Map<String,String>> resList=new ArrayList<>();
        List<Coach> list = coachService.getCoachByClubId(club_id);
        logger.info("club_id ====" + club_id);
        if(list==null){
            Map<String,String> map = new HashMap<>();
            map.put("resCode","404");
            map.put("resInfo","该门店暂时还没有教练哦");
            resList.add(map);
            return resList;
        }
        for(Coach c:list){
            String coachId=c.getCoachId();
            Map<String,String> map=coachService.getCoachInfoByView(coachId);
            String tmp= CommParams.WEB_URL+c.getIcon().replaceAll("/app/test","");
//            c.setIcon(tmp);

            map.put("icon",tmp);
            map.put("total_count",((Object)map.get("total_count")).toString());
            map.put("weight",((Object)map.get("weight")).toString());
            map.put("min_price",((Object)map.get("min_price")).toString());
            map.put("height",((Object)map.get("height")).toString());
            map.put("score",((Object)map.get("score")).toString());
            map.put("age",((Object)map.get("age")).toString());
//            map.put("birthday",((Object)map.get("birthday")).toString());
            map.remove("birthday");
            System.out.println(map);
            resList.add(map);
        }

        return resList;
    }

    @RequestMapping("/qryMember")
    public List<Object> qryMember(
            @RequestParam("club_id") String club_id
    ) {
        logger.info("查询场地的会员信息：club_id ====" + club_id );
        List<Map<Object,Object>> list = new ArrayList<>();

        List<Object> resList = new ArrayList<>();
        list=clubService.getMyMemberId(club_id);
        Iterator it = list.iterator();
        while(it.hasNext()) {
            String mem_id=it.next().toString();
            mem_id=mem_id.substring(mem_id.indexOf("=")+1).replaceAll("}","");
            Map<Object,Object> map=new HashMap<>();
            map.put("MemInfo",memberService.getMemberById(mem_id));
            map.put("LessInfo",qryMyMemSum(mem_id,club_id));

            resList.add(map);
            logger.info(mem_id);
        }
        return resList;

    }
    /**
     * 获取会员总课时、已完成、已预约、待预约
     * @param mem_id
     * @return
     */
    @RequestMapping("/qryMyMemSum")
    public Map<Object,Object> qryMyMemSum(
            @RequestParam("mem_id") String mem_id,
            @RequestParam("club_id") String club_id
    ){
        logger.info("mem_id ===="+mem_id);
        Map<Object,Object> map = new HashMap<>();
        map=memberService.getMemCourseInfo(mem_id,"",club_id);
        logger.info("map.get(rem)==="+map.get("rem"));

        //获取已预约的课时数
        List<Map<Object,Object>> list=new ArrayList<>();
        list=memberService.getMemberLessByView(mem_id,"",club_id,"0");
        logger.info("list.size()"+list.size());
        map.put("ordered",list.size());
        map.put("ordering",Integer.parseInt(map.get("rem").toString())-list.size());
        return map;
    }
}