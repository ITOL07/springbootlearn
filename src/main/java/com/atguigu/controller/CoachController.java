package com.atguigu.controller;


import com.atguigu.entity.Coach;
import com.atguigu.service.CoachService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@RestController
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CoachService coachService;

    @RequestMapping("/getCoach")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from coach where score>4 order by score desc ";
//        String sql = "select * from v_coach_lesson ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/qry")
    public Coach getCoach(
            @RequestParam("coach_id") String coach_id
    ) {
        System.out.println("coach_id===" + coach_id);
        System.out.println(coachService.getCoachById(coach_id));
        return coachService.getCoachById(coach_id);
    }

    @RequestMapping("/qryLesson")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("status") Integer status
    ) {
        System.out.println("mem_id ====" + coach_id + "status=====" + status);
//        List<Map<Object,Object>> list= new ArrayList<Map<Object,Object>>();
//        List<Map<Object,Object>> list_res= new ArrayList<Map<Object,Object>>();
//        list=memberService.getMemberLessByIdS(mem_id,status);

//        for(Map<Object,Object> map:list){
//            String sale_id=map.get("sale_id").toString();
//            System.out.println("sale_id====="+sale_id);
//            Course course  = courseService.getCourseById(sale_id);
//            map.put("")
//        }
//        return memberService.getMemberLessByIdS(mem_id,status);
        if (status != null) {
            return coachService.getCoachLessByView(coach_id, status);
        }
        else {
            return coachService.getCoachLessByView_id(coach_id);
        }
    }

    /**
     *
     * @param coach_id
     * @param reg_date
     * @return 教练日汇总结果
     */
    @RequestMapping("/qryLessReg")
    public List<Map<Object, Object>> qryLesson(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("reg_date") String reg_date
    ) {
        System.out.println("mem_id ====" + coach_id + "reg_date=====" + reg_date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            System.out.println(e);
        }
        return coachService.getCoachIncomeById(coach_id, date);

    }

    @RequestMapping("/qrySum")
    public Map<Object, Object> qrySum(
            @RequestParam("coach_id") String coach_id,
            @RequestParam("reg_date") String reg_date
    ) {
        System.out.println("mem_id ====" + coach_id + "reg_date=====" + reg_date);

        Map<Object,Object> map= new HashMap<Object,Object>();
        Map<Object,Object> resMap= new HashMap<Object,Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reg_date);

        } catch (ParseException e) {
            System.out.println(e);
        }

        map=coachService.getCoachIncomeSumById(coach_id, date);
        System.out.println("map.get(\"les_count_sum\");==========="+map.get("les_count_sum"));

        int les_count_sum=Integer.parseInt(map.get("les_count_sum").toString());
        int sold_count_sum=Integer.parseInt(map.get("les_count_sum").toString());

        float les_total_amt=Float.parseFloat(map.get("les_total_amt").toString());
        float sold_total_amt=Float.parseFloat(map.get("sold_total_amt").toString());

        float les_pct=coachService.getCoachIncomePct(les_count_sum,"JL_KT");
        float sold_pct=coachService.getCoachIncomePct(sold_count_sum,"JL_XT");

        float les_amt=les_total_amt*les_pct;
        float sold_amt=sold_total_amt*sold_pct;

        resMap.put("les_total_amt",les_amt);
        resMap.put("sold_total_amt",sold_amt);

        System.out.println("les_count_sum=["+les_count_sum+"]\t les_pct=["+les_pct+"]\t les_amt=["+les_amt+"]\t les_total_amt=["+les_total_amt+"]");
        System.out.println("sold_count_sum=["+sold_count_sum+"]\t sold_pct=["+sold_pct+"]\t sold_amt=["+sold_amt+"]\t sold_total_amt=["+sold_total_amt+"]");
        return resMap;

    }

}