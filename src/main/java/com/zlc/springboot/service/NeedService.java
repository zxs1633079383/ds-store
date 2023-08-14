package com.zlc.springboot.service;

import com.zlc.springboot.model.Need;

//软件定制Service层
public interface NeedService {
    int deleteByPrimaryKey(Integer needid);

    int insert(Need record);

    int insertSelective(Need record);

    Need selectByPrimaryKey(Integer needid);

    int updateByPrimaryKeySelective(Need record);

    int updateByPrimaryKeyWithBLOBs(Need record);

    int updateByPrimaryKey(Need record);
}
