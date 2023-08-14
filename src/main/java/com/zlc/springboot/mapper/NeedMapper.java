package com.zlc.springboot.mapper;

import com.zlc.springboot.model.Need;

public interface NeedMapper {
    int deleteByPrimaryKey(Integer needid);

    int insert(Need record);

    int insertSelective(Need record);

    Need selectByPrimaryKey(Integer needid);

    int updateByPrimaryKeySelective(Need record);

    int updateByPrimaryKeyWithBLOBs(Need record);

    int updateByPrimaryKey(Need record);
}