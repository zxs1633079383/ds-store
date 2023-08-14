package com.zlc.springboot.service;

import com.zlc.springboot.model.KeyMi;

//卡密Service
public interface KeyMiService {
    int deleteByPrimaryKey(Integer keyid);

    int insert(KeyMi record);

    int insertSelective(KeyMi record);

    KeyMi selectByPrimaryKey(Integer keyid);

    int updateByPrimaryKeySelective(KeyMi record);

    int updateByPrimaryKey(KeyMi record);

    //查询卡密是否过期
    int keyToOutTime(String keymi);

    //更改卡密使用状态
    int updateFroKeyState(String keymi);
}