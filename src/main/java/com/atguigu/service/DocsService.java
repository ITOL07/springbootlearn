package com.atguigu.service;

import com.atguigu.entity.TPubDocs;

public interface DocsService {
    public TPubDocs getDocByName(String type,String name);
}
