package com.zlc.springboot.model;

import java.io.Serializable;
import java.util.Date;

//用户实体类
public class Login  implements Serializable {
    //用户编号
    private Integer userid;

    //用户名
    private String user;

    //密码
    private String pwd;

    //创建时间(前端不用谢=写)
    private Date createtime;

    //注册需要的卡密
    private String userkey;

    //所绑定的qq号.
    private String qqname;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }



    public String getQqname() {
        return qqname;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname == null ? null : qqname.trim();
    }

    @Override
    public String toString() {
        return "Login{" +
                "userid=" + userid +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createtime=" + createtime +
                ", key='" + userkey + '\'' +
                ", qqname='" + qqname + '\'' +
                '}';
    }
}