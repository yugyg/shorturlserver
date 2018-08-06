package com.sun.entity;

import java.util.Date;

public class YgfLongShortLink {
    private Long id;

    private String longlink;

    private String shortlink;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLonglink() {
        return longlink;
    }

    public void setLonglink(String longlink) {
        this.longlink = longlink == null ? null : longlink.trim();
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
}