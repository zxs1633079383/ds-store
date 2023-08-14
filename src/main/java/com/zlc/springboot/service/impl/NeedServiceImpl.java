package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.NeedMapper;
import com.zlc.springboot.model.Need;
import com.zlc.springboot.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedServiceImpl implements NeedService {

    @Autowired
    private NeedMapper needMapper;

    @Override
    public int deleteByPrimaryKey(Integer needid) {
        return needMapper.deleteByPrimaryKey(needid);
    }

    @Override
    public int insert(Need record) {
        return needMapper.insert(record);
    }

    @Override
    public int insertSelective(Need record) {
        return needMapper.insertSelective(record);
    }

    @Override
    public Need selectByPrimaryKey(Integer needid) {
        return needMapper.selectByPrimaryKey(needid);
    }

    @Override
    public int updateByPrimaryKeySelective(Need record) {
        return needMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Need record) {
        return needMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Need record) {
        return needMapper.updateByPrimaryKey(record);
    }
}
