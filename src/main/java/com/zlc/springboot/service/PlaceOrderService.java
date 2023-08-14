package com.zlc.springboot.service;

import com.zlc.springboot.model.PlaceOrder;

public interface PlaceOrderService {
    int deleteByPrimaryKey(String outTradeNo);

    int insert(PlaceOrder record);

    int insertSelective(PlaceOrder record);

    PlaceOrder selectByPrimaryKey(String outTradeNo);

    int updateByPrimaryKeySelective(PlaceOrder record);

    int updateByPrimaryKey(PlaceOrder record);
}
