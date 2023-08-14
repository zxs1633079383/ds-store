package com.zlc.springboot.mapper;

import com.zlc.springboot.model.Sell;
import org.springframework.stereotype.Repository;


@Repository
public interface SellMapper {
    int deleteByPrimaryKey(Integer sellid);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(Integer sellid);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);

    //通过softid查询该软件是否被下载过
    int selectCountBySoftid(Integer softid);

    //通过softid更新下单总览表中的数据
    int updateDownBySoftid(Integer softid);

    //获取订单交总额
    int moneyAll();
}