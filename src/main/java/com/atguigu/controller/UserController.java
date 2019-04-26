package com.atguigu.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@RestController
@RequestMapping("/mydb")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUsers")
    public List<Map<String, Object>> getDbType() {
//        String sql = "select * from member";
        String sql = "select distinct sale_id from order_dtl where mem_id='201904050002'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    logger.info(key + ":" + value);
                }
            }
        }
        return list;
    }

    @RequestMapping("/addUser")
    public void add(){

    }
}