package com.zlc.springboot.mapper;

import com.zlc.springboot.model.IpAddress;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//ip的Mapper映射文件
@Repository
public interface IpAddressMapper {
    int insert(IpAddress record);

    int insertSelective(IpAddress record);

    int selectCountByDay(String date);

    //统计PV
    List<Map<Object,Object>> selectPV();
    //统计UV
    List<Map<Object,Object>> selectUv();
}