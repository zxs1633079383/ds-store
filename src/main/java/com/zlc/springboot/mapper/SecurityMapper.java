package com.zlc.springboot.mapper;

import com.zlc.springboot.model.Security;
import org.springframework.stereotype.Repository;

//用户权限管理Mapper
@Repository
public interface SecurityMapper {
    int deleteByPrimaryKey(Integer securityid);

    int insert(Security record);

    int insertSelective(Security record);

    Security selectByPrimaryKey(Integer securityid);

    int updateByPrimaryKeySelective(Security record);

    int updateByPrimaryKey(Security record);
}