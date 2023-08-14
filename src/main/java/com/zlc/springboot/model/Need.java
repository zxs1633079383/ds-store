package com.zlc.springboot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Need  implements Serializable {
    private Integer needid;

    private String needname;

    private String company;

    private BigDecimal price;

    private String contacts;

    private Integer telphone;

    private String wechatnum;

    private String specefication;

    private Date needdate;

    public Date getNeeddate() {
        return needdate;
    }

    public void setNeeddate(Date needdate) {
        this.needdate = needdate;
    }

    public Integer getNeedid() {
        return needid;
    }

    public void setNeedid(Integer needid) {
        this.needid = needid;
    }

    public String getNeedname() {
        return needname;
    }

    public void setNeedname(String needname) {
        this.needname = needname == null ? null : needname.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public Integer getTelphone() {
        return telphone;
    }

    public void setTelphone(Integer telphone) {
        this.telphone = telphone;
    }

    public String getWechatnum() {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum) {
        this.wechatnum = wechatnum == null ? null : wechatnum.trim();
    }

    public String getSpecefication() {
        return specefication;
    }

    public void setSpecefication(String specefication) {
        this.specefication = specefication == null ? null : specefication.trim();
    }

    @Override
    public String toString() {
        return "Need{" +
                "needid=" + needid +
                ", needname='" + needname + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", contacts='" + contacts + '\'' +
                ", telphone=" + telphone +
                ", wechatnum='" + wechatnum + '\'' +
                ", specefication='" + specefication + '\'' +
                ", needdate=" + needdate +
                '}';
    }
}