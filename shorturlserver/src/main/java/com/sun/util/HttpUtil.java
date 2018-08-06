package com.sun.util;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HTTP请求工具类
 * 
 * @author sunning
 *
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static final OkHttpClient client = new OkHttpClient();

	private static final HttpUtil instance = new HttpUtil();

	private HttpUtil() {
		
	}

	public static HttpUtil getInstance() {
		return instance;
	}

	/**
	 * 执行http post请求
	 * 
	 * @param postBody
	 * @param url
	 * @return
	 */
	public String httpExecute(String url) {

		Request request = new Request.Builder().url(url).get().build();
		try {
			Response response = client.newCall(request).execute();

			byte[] bodyBytes = response.body().bytes();
			String postBodyStr = new String(bodyBytes, Charset.forName("utf-8"));
			return postBodyStr;

		} catch (Exception e) {
			logger.debug(" 网络调用异常httpExecute : {}", e);
		}
		return null;
	}

	/**
	 * 转换成指定对象
	 * 
	 * @param postBody
	 * @param url
	 * @param clazz
	 * @return
	 */
	public Object httpExecute(String url, Class clazz) {
		String rString = httpExecute(url);
		if (rString != null) {
			Object obj = JSONObject.parseObject(rString, clazz);
			return obj;
		}
		return null;

	}
}
