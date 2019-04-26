package com.atguigu.dao;

import com.atguigu.entity.TPubDocs;
import org.apache.ibatis.annotations.Param;

public interface TPubDocsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPubDocs record);

    int insertSelective(TPubDocs record);

    TPubDocs selectByPrimaryKey(Integer id);

    TPubDocs selectByName(@Param("type") String type,@Param("name") String name);

    int updateByPrimaryKeySelective(TPubDocs record);

    int updateByPrimaryKey(TPubDocs record);


}