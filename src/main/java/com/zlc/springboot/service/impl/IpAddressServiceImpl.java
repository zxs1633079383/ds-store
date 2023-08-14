package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.IpAddressMapper;
import com.zlc.springboot.model.IpAddress;
import com.zlc.springboot.service.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressMapper ipAddressMapper;

    @Override
    public int insert(IpAddress record) {
        return ipAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(IpAddress record) {
        return ipAddressMapper.insertSelective(record);
    }

    @Override
    public int selectCountByDay(String date) {
        return ipAddressMapper.selectCountByDay(date);
    }

    @Override
    public List<Map<Object, Object>> selectPV() {
        return ipAddressMapper.selectPV();
    }

    @Override
    public List<Map<Object, Object>> selectUv() {
        return ipAddressMapper.selectUv();
    }
}
