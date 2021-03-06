package com.self.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "testinfo",type = "info")
public class Info implements Serializable {
    private String name;
    private int id;
    private int maxchannelcount;
    private int curchannelcount;
    private int code;
    private String msg;

    public int getMaxchannelcount() {
        return maxchannelcount;
    }

    public void setMaxchannelcount(int maxchannelcount) {
        this.maxchannelcount = maxchannelcount;
    }

    public int getCurchannelcount() {
        return curchannelcount;
    }

    public void setCurchannelcount(int curchannelcount) {
        this.curchannelcount = curchannelcount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
