package com.sun.service.data;

/**
 * 最近七天数据
 * @author jiangchao1
 *
 */
public class SevenDaysData {
	/**总数*/
	private String total;
	/**uv总数*/
	private String uv;
	/**ip总数*/
	private String ipnum;
	/**正常连接*/
	private String oklink;
	/**错误连接*/
	private String errorlink;
	/**z轴信息*/
	private String xData;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public String getIpnum() {
		return ipnum;
	}
	public void setIpnum(String ipnum) {
		this.ipnum = ipnum;
	}
	public String getOklink() {
		return oklink;
	}
	public void setOklink(String oklink) {
		this.oklink = oklink;
	}
	public String getErrorlink() {
		return errorlink;
	}
	public void setErrorlink(String errorlink) {
		this.errorlink = errorlink;
	}
	public String getxData() {
		return xData;
	}
	public void setxData(String xData) {
		this.xData = xData;
	}
}
