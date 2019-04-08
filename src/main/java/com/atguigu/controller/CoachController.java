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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
        System.out.println("coach_id==="+coach_id);
        System.out.println(coachService.getCoachById(coach_id));
        return coachService.getCoachById(coach_id);
    }

}