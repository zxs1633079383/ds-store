package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.InitOrderMapper;
import com.zlc.springboot.model.InitOrder;
import com.zlc.springboot.service.InitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InitOrderServiceImpl implements InitOrderService {


    //注入Mapper层
    @Autowired
    private InitOrderMapper initOrderMapper;

    @Override
    public int deleteByPrimaryKey(String outTradeNo) {
        return initOrderMapper.deleteByPrimaryKey(outTradeNo);
    }

    @Override
    public int insert(InitOrder record) {
        return initOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(InitOrder record) {
        return initOrderMapper.insertSelective(record);
    }

    @Override
    public InitOrder selectByPrimaryKey(String outTradeNo)
    {
        return initOrderMapper.selectByPrimaryKey(outTradeNo);
    }

    @Override
    public int updateByPrimaryKeySelective(InitOrder record) {

        return initOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InitOrder record) {
        return initOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStateById(String outTradeNo) {
        return initOrderMapper.updateStateById(outTradeNo);
    }

    @Override
    public String FilePathBySoftID(String  softid) {
        return initOrderMapper.FilePathBySoftID(softid);
    }

    @Override
    public Integer SelectIdByOrderID(String orderID) {
        return initOrderMapper.SelectIdByOrderID(orderID);
    }

    @Override
    public Integer ZeroByOrderState() {
        return initOrderMapper.ZeroByOrderState();
    }

    @Override
    public Integer OneByOrderState() {
        return initOrderMapper.OneByOrderState();
    }

    @Override
    public Map<Object, Object> selectNumAndMoney() {
        return initOrderMapper.selectNumAndMoney();
    }

    @Override
    public Integer selectPageOfAll() {
        return initOrderMapper.selectPageOfAll();
    }

    @Override
    public List<InitOrder> selectAllOfPage(Integer start, Integer end) {
        return initOrderMapper.selectAllOfPage(start,end);
    }

    @Override
    public Map<Object, Object> selectAllNumAndMoney() {
        return initOrderMapper.selectAllNumAndMoney();
    }

    @Override
    public List<Map<Object,Object>> selectFileAllOfMoney() {
        return initOrderMapper.selectFileAllOfMoney();
    }
}
