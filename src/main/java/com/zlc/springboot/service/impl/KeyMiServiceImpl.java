package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.KeyMiMapper;
import com.zlc.springboot.model.KeyMi;
import com.zlc.springboot.service.KeyMiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyMiServiceImpl implements KeyMiService {

    //注入KeyMiMapper
    @Autowired
    private KeyMiMapper keyMiMapper;

    @Override
    public int deleteByPrimaryKey(Integer keyid) {
        return keyMiMapper.deleteByPrimaryKey(keyid);
    }

    @Override
    public int insert(KeyMi record) {
        return keyMiMapper.insert(record);
    }

    @Override
    public int insertSelective(KeyMi record) {
        return keyMiMapper.insertSelective(record);
    }

    @Override
    public KeyMi selectByPrimaryKey(Integer keyid) {
        return keyMiMapper.selectByPrimaryKey(keyid);
    }

    @Override
    public int updateByPrimaryKeySelective(KeyMi record) {

        return keyMiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(KeyMi record) {

        return keyMiMapper.updateByPrimaryKey(record);
    }

    @Override
    public int keyToOutTime(String keymi) {
        return keyMiMapper.keyToOutTime(keymi);
    }

    @Override
    public int updateFroKeyState(String keymi) {
        return keyMiMapper.updateFroKeyState(keymi);
    }
}
