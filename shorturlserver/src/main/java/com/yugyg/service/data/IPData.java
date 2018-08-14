package com.yugyg.service.data;

/**
 * ip数据
 * @author jiangchao1
 *
 */
public class IPData {
	/**访问次数*/
	private Integer count;
	/**归属地*/
	private String ipBelong;
	/**ip*/
	private String ip;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getIpBelong() {
		return ipBelong;
	}
	public void setIpBelong(String ipBelong) {
		this.ipBelong = ipBelong;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
