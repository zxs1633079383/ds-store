package com.zlc.springboot.model;

import java.io.Serializable;

public class Sell implements Serializable {
    private Integer sellid;

    private Integer sellnumcount;

    private Double sellsummoney;

    private Integer softid;

    public Integer getSellid() {
        return sellid;
    }

    public void setSellid(Integer sellid) {
        this.sellid = sellid;
    }

    public Integer getSellnumcount() {
        return sellnumcount;
    }

    public void setSellnumcount(Integer sellnumcount) {
        this.sellnumcount = sellnumcount;
    }

    public Double getSellsummoney() {
        return sellsummoney;
    }

    public void setSellsummoney(Double sellsummoney) {
        this.sellsummoney = sellsummoney;
    }

    public Integer getSoftid() {
        return softid;
    }

    public void setSoftid(Integer softid) {
        this.softid = softid;
    }
}