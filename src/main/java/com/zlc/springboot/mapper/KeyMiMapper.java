package com.zlc.springboot.mapper;

import com.zlc.springboot.model.KeyMi;
import org.springframework.stereotype.Repository;

//卡密Mapper
@Repository
public interface KeyMiMapper {
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