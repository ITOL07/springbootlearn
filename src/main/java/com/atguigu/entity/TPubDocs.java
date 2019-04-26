package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain=true)
public class TPubDocs {
    private Integer id;

    private String type;

    private String name;

    private String chName;

    private String bz1;

    private String bz2;

    private Date upTime;

    private String url;

}