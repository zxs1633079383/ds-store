package com.zlc.springboot.service;

import com.zlc.springboot.model.CodeUrl;

public interface CodeUrlService {
    int deleteByPrimaryKey(String outTradeNo);

    int insert(CodeUrl record);

    int insertSelective(CodeUrl record);

    CodeUrl selectByPrimaryKey(String outTradeNo);

    int updateByPrimaryKeySelective(CodeUrl record);

    int updateByPrimaryKey(CodeUrl record);
}
