package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.CodeUrlMapper;
import com.zlc.springboot.model.CodeUrl;
import com.zlc.springboot.service.CodeUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeUrlServiceImpl implements CodeUrlService {

    @Autowired
    private CodeUrlMapper codeUrlMapper;

    @Override
    public int deleteByPrimaryKey(String outTradeNo) {
        return codeUrlMapper.deleteByPrimaryKey(outTradeNo);
    }

    @Override
    public int insert(CodeUrl record) {

        return codeUrlMapper.insert(record);
    }

    @Override
    public int insertSelective(CodeUrl record) {

        return codeUrlMapper.insertSelective(record);
    }

    @Override
    public CodeUrl selectByPrimaryKey(String outTradeNo) {

        return codeUrlMapper.selectByPrimaryKey(outTradeNo);
    }

    @Override
    public int updateByPrimaryKeySelective(CodeUrl record) {

        return codeUrlMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CodeUrl record) {

        return codeUrlMapper.updateByPrimaryKey(record);
    }
}
