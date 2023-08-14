package com.zlc.springboot.mapper;

import com.zlc.springboot.model.PlaceOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceOrderMapper {
    int deleteByPrimaryKey(String outTradeNo);

    int insert(PlaceOrder record);

    int insertSelective(PlaceOrder record);

    PlaceOrder selectByPrimaryKey(String outTradeNo);

    int updateByPrimaryKeySelective(PlaceOrder record);

    int updateByPrimaryKey(PlaceOrder record);
}