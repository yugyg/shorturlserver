package com.yugyg.service.data;

/**
 * 公用搜索类
 * @author jiangchao1
 *
 */
public class SearchData {
	/**开始时间*/
	private String begin;
	/**结束时间*/
	private String end;
	/**分组字段*/
	private String groupColumn;
	/**短链接*/
	private String shortUrl;
	/**长链接*/
	private String longUrl;
	/**浏览器*/
	private String browser;
	/**设备*/
	private String equipment;
	/**国家*/
	private String country;
	/**网络供应商*/
	private String internet;
	/**跳转状态*/
	private String status;
	/**省*/
	private String province;
	/**pageNum*/
	private Integer startIndex = 0;
	/**desc描述*/
	private String desc;
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getGroupColumn() {
		return groupColumn;
	}
	public void setGroupColumn(String groupColumn) {
		this.groupColumn = groupColumn;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
