package com.atguigu.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class indexController {
    @RequestMapping("/index/")
    public String index(){
        return "/index";
    }

    @RequestMapping("/test*.html")
    public String test(){
        return "/test";
    }
}
