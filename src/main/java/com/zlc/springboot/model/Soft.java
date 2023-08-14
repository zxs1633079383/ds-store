package com.zlc.springboot.model;

import java.io.Serializable;
import java.util.Date;

public class Soft implements Serializable {
    private Integer softid;

    private String softname;

    private Double softlength;

    private Double softprice;

    private String softfather;

    private Date softcreatime;

    private String softiconpath;

    private String softimagepath;

    private String softfileurl;

    private Integer softstate;

    private String softtext;

    public String getSofttext() {
        return softtext;
    }

    public void setSofttext(String softtext) {
        this.softtext = softtext;
    }

    public Integer getSoftid() {
        return softid;
    }

    public void setSoftid(Integer softid) {
        this.softid = softid;
    }

    public String getSoftname() {
        return softname;
    }

    public void setSoftname(String softname) {
        this.softname = softname == null ? null : softname.trim();
    }

    public Double getSoftlength() {
        return softlength;
    }

    public void setSoftlength(Double softlength) {
        this.softlength = softlength;
    }

    public Double getSoftprice() {
        return softprice;
    }

    public void setSoftprice(Double softprice) {
        this.softprice = softprice;
    }

    public String getSoftfather() {
        return softfather;
    }

    public void setSoftfather(String softfather) {
        this.softfather = softfather == null ? null : softfather.trim();
    }

    public Date getSoftcreatime() {
        return softcreatime;
    }

    public void setSoftcreatime(Date softcreatime) {
        this.softcreatime = softcreatime;
    }

    public String getSofticonpath() {
        return softiconpath;
    }

    public void setSofticonpath(String softiconpath) {
        this.softiconpath = softiconpath == null ? null : softiconpath.trim();
    }

    public String getSoftimagepath() {
        return softimagepath;
    }

    public void setSoftimagepath(String softimagepath) {
        this.softimagepath = softimagepath == null ? null : softimagepath.trim();
    }

    public String getSoftfileurl() {
        return softfileurl;
    }

    public void setSoftfileurl(String softfileurl) {
        this.softfileurl = softfileurl == null ? null : softfileurl.trim();
    }

    public Integer getSoftstate() {
        return softstate;
    }

    public void setSoftstate(Integer softstate) {
        this.softstate = softstate;
    }

    @Override
    public String toString() {
        return "Soft{" +
                "softid=" + softid +
                ", softname='" + softname + '\'' +
                ", softlength=" + softlength +
                ", softprice=" + softprice +
                ", softfather='" + softfather + '\'' +
                ", softcreatime=" + softcreatime +
                ", softiconpath='" + softiconpath + '\'' +
                ", softimagepath='" + softimagepath + '\'' +
                ", softfileurl='" + softfileurl + '\'' +
                ", softstate=" + softstate +
                ", softtext='" + softtext + '\'' +
                '}';
    }
}