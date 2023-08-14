package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.SellMapper;
import com.zlc.springboot.model.Sell;
import com.zlc.springboot.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellMapper sellMapper;

    @Override
    public int deleteByPrimaryKey(Integer sellid) {
        return sellMapper.deleteByPrimaryKey(sellid);
    }

    @Override
    public int insert(Sell record) {
        return sellMapper.insert(record);
    }

    @Override
    public int insertSelective(Sell record) {
        return sellMapper.insertSelective(record);
    }

    @Override
    public Sell selectByPrimaryKey(Integer sellid) {
        return sellMapper.selectByPrimaryKey(sellid);
    }

    @Override
    public int updateByPrimaryKeySelective(Sell record) {
        return updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Sell record) {
        return sellMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectCountBySoftid(Integer softid) {
        return sellMapper.selectCountBySoftid(softid);
    }

    @Override
    public int updateDownBySoftid(Integer softid) {
        return sellMapper.updateDownBySoftid(softid);
    }

    @Override
    public int moneyAll() {
        return sellMapper.moneyAll();
    }
}
