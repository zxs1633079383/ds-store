package com.zlc.springboot.model;

import java.io.Serializable;

//订单初始状态
public class InitOrder implements Serializable {
    private String outTradeNo;

    private Double totalAmount;

    private String subject;

    private String body;

    private Integer userid;

    private Integer softid;

    private Integer orderstate;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSoftid() {
        return softid;
    }

    public void setSoftid(Integer softid) {
        this.softid = softid;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    @Override
    public String toString() {
        return "InitOrder{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", userid=" + userid +
                ", softid=" + softid +
                ", orderstate=" + orderstate +
                '}';
    }
}