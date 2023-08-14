package com.zlc.springboot.mapper;

import com.zlc.springboot.model.Login;
import org.springframework.stereotype.Repository;

import java.util.List;

//用户Mapper
@Repository
public interface LoginMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);

    //判断账号和密码是否输入正确
    Login isUser(String user, String pwd);

    //根据用户名查询用户id
    Integer findUserid(String user);

    //3.查询用户数量
    Integer selectUserNum();

    //4.查询所有用户
    List<Login> selectAll(Integer start,Integer end);

    //5. 查询分页插件数量
    Integer selectNumOfAll();
}