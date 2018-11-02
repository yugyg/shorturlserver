package com.yugyg.entity;

import java.util.Date;

public class YgfLongShortLink {
    private Long id;

    private String shortlink;

    private Date time;

    private String desc;

    private String longlink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortlink() {
        return shortlink;
    }

    public void setShortlink(String shortlink) {
        this.shortlink = shortlink == null ? null : shortlink.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getLonglink() {
        return longlink;
    }

    public void setLonglink(String longlink) {
        this.longlink = longlink == null ? null : longlink.trim();
    }
}