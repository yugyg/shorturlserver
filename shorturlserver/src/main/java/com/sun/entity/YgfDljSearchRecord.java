package com.sun.entity;

import java.util.Date;

public class YgfDljSearchRecord {
    private Long id;

    private Date time;

    private String ip;

    private String browser;

    private String equipment;

    private String searchshort;

    private String ipbelong;

    private String resolution;

    private Integer status;

    private String cookie;

    private String referer;

    private String useragent;

    private String phonenum;

    private String searchlong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    public String getSearchshort() {
        return searchshort;
    }

    public void setSearchshort(String searchshort) {
        this.searchshort = searchshort == null ? null : searchshort.trim();
    }

    public String getIpbelong() {
        return ipbelong;
    }

    public void setIpbelong(String ipbelong) {
        this.ipbelong = ipbelong == null ? null : ipbelong.trim();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent == null ? null : useragent.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getSearchlong() {
        return searchlong;
    }

    public void setSearchlong(String searchlong) {
        this.searchlong = searchlong == null ? null : searchlong.trim();
    }
}