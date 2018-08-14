package com.yugyg.service.data;

/**
 * 顶部三个数据
 * @author jiangchao1
 *
 */
public class TopThree {
	/**访问总数*/
	private Integer total;
	/**访问ip总数*/
	private Integer ip;
	/**访问uv总数*/
	private Integer uv;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	
}
