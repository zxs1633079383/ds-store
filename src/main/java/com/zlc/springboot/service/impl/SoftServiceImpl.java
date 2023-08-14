package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.SoftMapper;
import com.zlc.springboot.model.Soft;
import com.zlc.springboot.service.SoftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "SoftCache")
@Service
public class SoftServiceImpl implements SoftService {

    @Autowired
    private SoftMapper softMapper;

    @Cacheable(value = "key1",key = "#key") //#p0表示第一个参数.
    @Override
    public List<Soft> selectSoftAll(String key) {
        return softMapper.selectSoftAll(key);
    }

    @Cacheable(value = "key1",key = "#key") //#p0表示第一个参数.
    @Override
    public List<Soft> selectSoftOfBoutique(String key) {
        return softMapper.selectSoftOfBoutique(key);
    }

    @Cacheable(value = "key1",key = "#key") //#p0表示第一个参数.
    @Override
    public List<Soft> selectSoftOfManyCount(String key) {
        return softMapper.selectSoftOfManyCount(key);
    }

    @Cacheable(value = "key1",key = "#key") //#p0表示第一个参数.
    @Override
    public List<Soft> selectSoftOfFree(String key) {
        return softMapper.selectSoftOfFree(key);
    }

    @Override
    public String selectNameById(Integer softid) {
        return softMapper.selectNameById(softid);
    }

    @Override
    public List<Soft> selectpageAllSoft(Integer start, Integer end) {
        return softMapper.selectpageAllSoft(start,end);
    }

    @Override
    public Integer selectCjNum() {
        return softMapper.selectCjNum();
    }

    @Override
    public int deleteByPrimaryKey(Integer softid) {
        return softMapper.deleteByPrimaryKey(softid);
    }

    @Override
    public int insert(Soft record) {
        return
                softMapper.insert(record);
    }

    @Override
    public int insertSelective(Soft record) {
        return softMapper.insertSelective(record);
    }

    @Override
    public Soft selectByPrimaryKey(Integer softid) {
        return softMapper.selectByPrimaryKey(softid);
    }

    @Override
    public int updateByPrimaryKeySelective(Soft record) {
        return softMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Soft record) {
        return softMapper.updateByPrimaryKey(record);
    }
}
