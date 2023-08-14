package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.PlaceOrderMapper;
import com.zlc.springboot.model.PlaceOrder;
import com.zlc.springboot.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//下单订单详情信息
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private PlaceOrderMapper placeOrderMapper;

    @Override
    public int deleteByPrimaryKey(String outTradeNo) {
        return placeOrderMapper.deleteByPrimaryKey(outTradeNo);
    }

    @Override
    public int insert(PlaceOrder record) {
        return placeOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(PlaceOrder record) {
        return placeOrderMapper.insertSelective(record);
    }

    @Override
    public PlaceOrder selectByPrimaryKey(String outTradeNo) {
        return placeOrderMapper.selectByPrimaryKey(outTradeNo);
    }

    @Override
    public int updateByPrimaryKeySelective(PlaceOrder record) {
        return placeOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlaceOrder record) {
        return placeOrderMapper.updateByPrimaryKey(record);
    }
}
