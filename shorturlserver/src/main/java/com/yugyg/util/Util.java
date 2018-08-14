package com.yugyg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {
	//判断浏览器
	public static String getBrowser(String user_agent) {
		String result= "";
		if(user_agent.indexOf("SogouMobileBrowser") != -1){//    "SogouMobileBrowser":"搜狗手机浏览器",
			result = "SogouMobileBrowser";
		}else if(user_agent.indexOf("UCBrowser") != -1) {//    "UCBrowser":"UC浏览器",
			result = "UCBrowser";
		}else if(user_agent.indexOf("UCWEB") != -1) {//    "UCWEB":"UC浏览器",
			result = "UCWEB";
		}else if(user_agent.indexOf("Opera") != -1) {//    "Opera":"Opera浏览器",
			result = "Opera";
		}else if(user_agent.indexOf("QQBrowser") != -1) {//    "QQBrowser":"QQ浏览器",
			result = "QQBrowser";
		}else if(user_agent.indexOf("TencentTraveler") != -1) {//    "TencentTraveler":"QQ浏览器",
			result = "TencentTraveler";
		}else if(user_agent.indexOf("MetaSr") != -1) {//    "MetaSr":"搜狗浏览器",
			result = "MetaSr";
		}else if(user_agent.indexOf("360SE") != -1) {//    "360SE":"360浏览器",
			result = "360SE";
		}else if(user_agent.indexOf("The world") != -1) {//    "The world":"世界之窗浏览器",
			result = "The world";
		}else if(user_agent.indexOf("Maxthon") != -1) {//    "Maxthon":"遨游浏览器",
			result = "Maxthon";
		}else if(user_agent.indexOf("MicroMessenger") != -1) {//    "MicroMessenger":"微信浏览器",
			result = "MicroMessenger";
		}else if(user_agent.indexOf("Chrome") != -1) {//    "Chrome":"谷歌浏览器",
			result = "Chrome";
		}else {
			result = "unknown";
		}
		return result;
	};
	//判断操作系统
	public static String getEquipment(String User_Agent) {
		String result= "";
		if (User_Agent.contains("Android")) {
			result = "Android";
		} else if (User_Agent.contains("iPhone")) {
			result = "iPhone";
		} else if (User_Agent.contains("iPad")) {
			result = "iPad";
		} else if (User_Agent.contains("Windows")) {
			result = "Windows";
		} else if (User_Agent.contains("Linux")) {
			result = "Linux";
		}else {
			result = "unknown";
		}
		return result;
	};
	// 时间格式转换为字符串格式
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String str = null;
		try {
			str = f.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request,String name) {
		Cookie[] cookies = request.getCookies();
		if (name != "" && name != null && null != cookies) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	/**
	 * 判断是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if(value == "" || value == null) {
			return true;
		}else {
			return false;
		}
	}
	// 获取访问端IP地址
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
