package com.yugyg.shorturlserver;

public class UrlMessage {

	private boolean flag; // 转换是否成功
	private String message;// 状态描述
	private String url;// 长链接

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlMessage [flag=" + flag + ", message=" + message + ", url=" + url + "]";
	}

}
