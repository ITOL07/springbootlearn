package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
//mybatis-generator:generate -e

@SpringBootApplication
@MapperScan("com.atguigu.dao")
public class helloworldMainApp  {
    public helloworldMainApp(){

    }
    public static void main(String[] args) {
        SpringApplication.run(helloworldMainApp.class,args);
    }
}
