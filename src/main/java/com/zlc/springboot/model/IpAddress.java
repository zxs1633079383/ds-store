package com.zlc.springboot.model;

import java.io.Serializable;
import java.util.Date;

public class IpAddress implements Serializable {
    private Integer ipid;

    private String address;

    private Date iptotime;

    private String ipstate;

    private String country;


    public Integer getIpid() {
        return ipid;
    }

    public void setIpid(Integer ipid) {
        this.ipid = ipid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getIptotime() {
        return iptotime;
    }

    public void setIptotime(Date iptotime) {
        this.iptotime = iptotime;
    }

    public String getIpstate() {
        return ipstate;
    }

    public void setIpstate(String ipstate) {
        this.ipstate = ipstate == null ? null : ipstate.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "ipid=" + ipid +
                ", address='" + address + '\'' +
                ", iptotime=" + iptotime +
                ", ipstate='" + ipstate + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}