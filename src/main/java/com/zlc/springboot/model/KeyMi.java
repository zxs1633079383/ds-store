package com.zlc.springboot.model;

import java.io.Serializable;

//卡密实体类
public class KeyMi  implements Serializable {
    private Integer keyid;

    private String key;

    private Integer quanx;

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getQuanx() {
        return quanx;
    }

    public void setQuanx(Integer quanx) {
        this.quanx = quanx;
    }

    @Override
    public String toString() {
        return "KeyMi{" +
                "keyid=" + keyid +
                ", key='" + key + '\'' +
                ", quanx=" + quanx +
                '}';
    }
}