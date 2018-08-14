package com.sun.service.data;

import java.util.Map;
/**
 * 公共返回对象
 * @author jiangchao1
 *
 */
public class ResultEntity {
	/**结果*/
	private String result;
	/**描述*/
	private String desc;
	/**错误代码*/
	private String errorCode;
	/**返回对象*/
	private Map<String, ?> data;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Map<String, ?> getData() {
		return data;
	}
	public void setData(Map<String, ?> data) {
		this.data = data;
	}
	
}
