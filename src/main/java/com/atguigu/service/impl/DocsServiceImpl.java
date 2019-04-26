package com.atguigu.service.impl;

import com.atguigu.dao.TPubDocsMapper;
import com.atguigu.entity.TPubDocs;
import com.atguigu.service.DocsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("docsService")
public class DocsServiceImpl implements DocsService{
    @Resource
    TPubDocsMapper tPubDocsMapper;
    @Override
    public TPubDocs getDocByName(String type,String name) {
        return tPubDocsMapper.selectByName(type,name);
    }
}
