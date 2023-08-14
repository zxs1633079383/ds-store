package com.zlc.springboot.model;

import java.io.Serializable;
import java.util.Date;

//下单详情信息
public class PlaceOrder implements Serializable {
    //订单号
    private String outTradeNo;
    //域名对应的appid
    private String appId;
    //贸易号
    private String tradeNo;
    //卖家ID
    private String sellerId;
    //下单时间
    private Date timestamp;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", appId='" + appId + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}