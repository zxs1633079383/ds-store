package com.zlc.springboot.model;

import java.io.Serializable;
import java.util.Date;

//用户管理实体类
public class Security implements Serializable {
    private Integer securityid;

    private Integer userid;

    private Integer usersecurity;

    private Date usersecuritytime;

    public Integer getSecurityid() {
        return securityid;
    }

    public void setSecurityid(Integer securityid) {
        this.securityid = securityid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUsersecurity() {
        return usersecurity;
    }

    public void setUsersecurity(Integer usersecurity) {
        this.usersecurity = usersecurity;
    }

    public Date getUsersecuritytime() {
        return usersecuritytime;
    }

    public void setUsersecuritytime(Date usersecuritytime) {
        this.usersecuritytime = usersecuritytime;
    }
}