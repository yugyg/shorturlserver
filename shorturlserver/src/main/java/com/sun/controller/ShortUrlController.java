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

package com.sun.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSONObject;
import com.sun.entity.YgfLongShortLink;
import com.sun.service.ShortUrlService;
import com.sun.util.Const;
import com.sun.util.Util;

@RestController
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
	
	@RequestMapping("/{shortUrl}")
	public void shortToLongUrl(@PathVariable("shortUrl") String shortUrl, HttpServletRequest httpRequest,
			WebRequest webRequest,HttpServletResponse httpResponse) throws ServletException, IOException {

		String remoteAddr = httpRequest.getRemoteAddr();//ip
		String userAgent = webRequest.getHeader(HttpHeaders.USER_AGENT);//请求头
		String referer = webRequest.getHeader(HttpHeaders.REFERER);
//		String remoteHost = httpRequest.getRemoteHost();//ip
//		String host = webRequest.getHeader(HttpHeaders.HOST);
//		String server = webRequest.getHeader(HttpHeaders.SERVER);
		//通过淘宝IP获取地址
		if (shortUrl == null || shortUrl == "") {
			httpResponse.sendRedirect(Const.ygfIndex);
		}else {
			String url = shortUrlService.getLongUrl(shortUrl);
			if (url == null || url == "") {
				httpResponse.sendRedirect(Const.ygfIndex);
			}else {
				String c = Util.getCookieByName(httpRequest, "sus");
				String cookieString = "";
				if(Util.isEmpty(c)){
					cookieString = Util.dateFormat(new Date(), Const.cookieFormate)+"-"+remoteAddr;
					Cookie cookie = new Cookie("sus",cookieString);
					httpResponse.addCookie(cookie);
				}else {
					cookieString = c;
				}
				shortUrlService.insertRecord(remoteAddr,userAgent,shortUrl,null,cookieString,referer);
				httpResponse.sendRedirect(url);
			}
		}
	}
	/**
	 * 转换和保存长链接
	 * @param longUrl
	 * @param httpRequest
	 * @return
	 */
	@GetMapping("/saveSU")
	public JSONObject longToShortUrl(@RequestParam(required = true) String longUrl, HttpServletRequest httpRequest) {
		JSONObject json = new JSONObject();
		if (longUrl==""||longUrl==null) {
			json.put("result", "fasle");
			json.put("des", "参数错误");
			return json;
		}else {
			YgfLongShortLink ygfLongShortLink = shortUrlService.insertShortLink(longUrl);
			json.put("shortUrl", ygfLongShortLink.getShortlink());
			json.put("result", "success");
			return json;
		}
	}
}
