package com.zlc.springboot.mapper;

import com.zlc.springboot.model.CodeUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeUrlMapper {
    int deleteByPrimaryKey(String outTradeNo);

    int insert(CodeUrl record);

    int insertSelective(CodeUrl record);

    CodeUrl selectByPrimaryKey(String outTradeNo);

    int updateByPrimaryKeySelective(CodeUrl record);

    int updateByPrimaryKey(CodeUrl record);
}