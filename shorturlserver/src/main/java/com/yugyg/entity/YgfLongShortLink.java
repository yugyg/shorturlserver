package com.yugyg.entity;

import java.util.Date;

public class YgfLongShortLink {
    private Long id;

    private String shortlink;

    private Date time;

    private String urldesc;

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

    public String getUrldesc() {
        return urldesc;
    }

    public void setUrldesc(String urldesc) {
        this.urldesc = urldesc == null ? null : urldesc.trim();
    }

    public String getLonglink() {
        return longlink;
    }

    public void setLonglink(String longlink) {
        this.longlink = longlink == null ? null : longlink.trim();
    }
}