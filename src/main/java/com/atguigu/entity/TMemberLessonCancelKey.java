package com.atguigu.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@Accessors(chain=true)
public class TMemberLessonCancelKey {
    private String kcId;

    private Integer seqNo;

}