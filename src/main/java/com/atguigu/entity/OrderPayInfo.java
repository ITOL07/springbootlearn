package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class OrderPayInfo {
    private String orderNo;

    private String appId;

    private String noncestr;

    private String package1;

    private String sign;

    private String timestamp;

    private String resultCode;

    private String returnCode;

    private Date upTime;

    private String bz1;

    private String bz2;

    private Integer status;
}