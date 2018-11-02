/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yugyg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yugyg.entity.YgfLongShortLink;
import com.yugyg.entity.YgfProvince;
import com.yugyg.service.ShortUrlService;
import com.yugyg.service.data.ResultEntity;
import com.yugyg.service.data.YgfLongShortLinkEx;
import com.yugyg.util.Const;
import com.yugyg.util.Util;

@Controller
@CrossOrigin
public class ShortUrlController {
	/***
	 * 请求重定向
	 * @param shortUrl
	 * @param httpRequest
	 * @param webRequest
	 * @return
	 */
	@Resource
	private ShortUrlService shortUrlService;
	/**
	 * 请求转发
	 * @param shortUrl 短链
	 * @param httpRequest
	 * @param webRequest
	 * @param httpResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/{shortUrl}")
	public void shortToLongUrl(@PathVariable("shortUrl") String shortUrl, HttpServletRequest httpRequest,
			WebRequest webRequest,HttpServletResponse httpResponse) throws ServletException, IOException {

		String remoteAddr = Util.getRemoteHost(httpRequest);//ip
		String userAgent = webRequest.getHeader(HttpHeaders.USER_AGENT);//请求头
		String referer = webRequest.getHeader(HttpHeaders.REFERER);
//		String remoteHost = httpRequest.getRemoteHost();//ip
//		String host = webRequest.getHeader(HttpHeaders.HOST);
//		String server = webRequest.getHeader(HttpHeaders.SERVER);
		//通过淘宝IP获取地址
		String c = Util.getCookieByName(httpRequest, "sus");
		String cookieString = "";
		if(Util.isEmpty(c)){
			cookieString = Util.dateFormat(new Date(), Const.cookieFormate)+"-"+remoteAddr;
			Cookie cookie = new Cookie("sus",cookieString);
			httpResponse.addCookie(cookie);
		}else {
			cookieString = c;
		}
		new Thread(new InsertRecordThread(remoteAddr,userAgent,shortUrl,cookieString,referer)).start();
		if (shortUrl == null || shortUrl == ""|| userAgent == "") {
			httpResponse.sendRedirect(Const.ygfIndex);
		}else {
			String url = shortUrlService.getLongUrl(shortUrl);
			if (url == null || url == "") {
				httpResponse.sendRedirect(Const.ygfIndex);
			}else {
				httpResponse.sendRedirect(Util.isEmpty(userAgent)?Const.ygfIndex:url.replaceAll("(&|.?)ygfPhoneNum=[0-9]*", ""));//没有请求头的过滤
			}
		}
	}
	//线程类                       
	public class InsertRecordThread implements Runnable {
		private String remoteAddr;
        private String userAgent;
        private String shortUrl;
        private String cookieString;
        private String referer;
		public InsertRecordThread(String remoteAddr,String userAgent,String shortUrl,String cookieString,String referer) {
			this.remoteAddr = remoteAddr;
            this.userAgent = userAgent;
            this.shortUrl = shortUrl;
            this.cookieString = cookieString;
            this.referer = referer;
		}
		//null分辨率
        public void run() {
        	shortUrlService.insertRecord(remoteAddr,userAgent,shortUrl,null,cookieString,referer);
        } 
    }
	/**
	 * 转换和保存长链接
	 * @param longUrl
	 * @param httpRequest
	 * @return
	 */
	@PostMapping("/saveSU")
	@ResponseBody
	public ResultEntity longToShortUrl(@RequestParam(required = true) String longUrl, HttpServletRequest httpRequest,@RequestParam(required = false) String desc) {
		ResultEntity result = new ResultEntity();
		Map<String, String> map = new HashMap<String,String>();
		if (longUrl==""||longUrl==null) {
			result.setResult(Const.result_fail);
			result.setDesc("参数错误");
			return result;
		}else {
			YgfLongShortLink ygfLongShortLink = shortUrlService.insertShortLink(longUrl,desc);
			result.setResult(Const.result_success);
			map.put("shortUrl", httpRequest.getRequestURL().toString().replace(httpRequest.getServletPath(),"/")+ygfLongShortLink.getShortlink());
			map.put("longUrl", longUrl);
			result.setData(map);
			return result;
		}
	}
	/**
	 * 	获得长链接
	 * @param shortUrl 短链接
	 * @param httpRequest
	 * @return
	 */
	@GetMapping("/getLongUrl")
	@ResponseBody
	public ResultEntity getLongUrl(@RequestParam(required = true) String shortUrl) {
		ResultEntity result = new ResultEntity();
		if (shortUrl==""||shortUrl==null) {
			result.setResult(Const.result_fail);
			result.setDesc("参数错误");
			return result;
		}else {
			String longUrl = shortUrlService.getLongUrl(shortUrl);
			Map<String, String> map = new HashMap<String,String>();
			result.setResult(Const.result_success);
			map.put("longUrl", longUrl);
			result.setData(map);
			return result;
		}
	}
	/**
	 * 	获得省份
	 * @param shortUrl 短链接
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping("/getProvinces")
	@ResponseBody
	public List<YgfProvince> getProvinces() {
		return shortUrlService.getAllProvince();
	}
	@RequestMapping("/")
	public String index() {
		return "redirect:/html/saveUrl.html";
	}
	public static void main(String[] args) {
		System.out.println(Util.dateFormat(new Date(),Const.date_formate_1));
	}
	/**
	 * -长短链表格
	 * Description:  
	 * @author jiangchao1  
	 * @date 2018年11月2日  
	 * @version 1.0
	 */
	public List<YgfLongShortLinkEx> getAllLink(
			@RequestParam(required = false) String shortUrl,
			@RequestParam(required = false) String longUrl,
			@RequestParam(required = false) String desc,
			@RequestParam(required = false) String start,
			@RequestParam(required = false) String end
			) {
		return shortUrlService.getAllLink(shortUrl,longUrl,desc,start,end);
	}
}
