package com.zlc.springboot.service.impl;

import com.zlc.springboot.mapper.LoginMapper;
import com.zlc.springboot.model.Login;
import com.zlc.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户接口实现类
@Service
public class LoginServiceImpl implements LoginService {

    //注入LoginMapper
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return loginMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(Login record) {
        return loginMapper.insert(record);
    }

    @Override
    public int insertSelective(Login record) {
        return loginMapper.insertSelective(record);
    }

    @Override
    public Login selectByPrimaryKey(Integer userid) {
        return loginMapper.selectByPrimaryKey(userid);
    }

    @Override
    public int updateByPrimaryKeySelective(Login record) {
        return loginMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Login record) {

        return loginMapper.updateByPrimaryKey(record);
    }

    //判断账号和密码是否正确
    @Override
    public Login isUser(String user, String pwd) {
        return loginMapper.isUser(user, pwd);
    }

    @Override
    public Integer findUserid(String user) {
        return loginMapper.findUserid(user);
    }

    @Override
    public Integer selectUserNum() {
        return loginMapper.selectUserNum();
    }

    @Override
    public List<Login> selectAll(Integer start, Integer end) {
        return loginMapper.selectAll(start,end);
    }

    @Override
    public Integer selectNumOfAll() {
        return loginMapper.selectNumOfAll();
    }


}
