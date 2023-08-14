package com.zlc.springboot.service;

import com.zlc.springboot.model.IpAddress;

import java.util.List;
import java.util.Map;

public interface IpAddressService {
    int insert(IpAddress record);

    int insertSelective(IpAddress record);

    int selectCountByDay(String date);

    //统计PV
    List<Map<Object,Object>> selectPV();
    //统计UV
    List<Map<Object,Object>> selectUv();

}
