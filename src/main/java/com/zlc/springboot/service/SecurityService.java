package com.zlc.springboot.service;

import com.zlc.springboot.model.Security;

//用户权限管理Service
public interface SecurityService {
    int deleteByPrimaryKey(Integer securityid);

    int insert(Security record);

    int insertSelective(Security record);

    Security selectByPrimaryKey(Integer securityid);

    int updateByPrimaryKeySelective(Security record);

    int updateByPrimaryKey(Security record);
}