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

package com.yugyg.shorturlserver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ShortUrlController {

	@GetMapping("/s2l")
	public UrlMessage shortToLongUrl(@RequestParam(required = true) String shortUrl, HttpServletRequest httpRequest,
			WebRequest webRequest) {

		String remoteHost = httpRequest.getRemoteHost();
		String remoteAddr = httpRequest.getRemoteAddr();

		String referer = webRequest.getHeader(HttpHeaders.REFERER);
		String userAgent = webRequest.getHeader(HttpHeaders.USER_AGENT);
		String host = webRequest.getHeader(HttpHeaders.HOST);
		String server = webRequest.getHeader(HttpHeaders.SERVER);

		System.out.println(webRequest);
		
		
		//通过淘宝IP获取地址
		String s = HttpUtil.getInstance().httpExecute("http://ip.taobao.com/service/getIpInfo.php?ip="+remoteAddr);
		UrlMessage urlMessage = new UrlMessage();

		return urlMessage;
	}

	@GetMapping("/l2s")
	public UrlMessage longToShortUrl(@RequestParam(required = true) String longUrl, HttpServletRequest httpRequest) {

		UrlMessage urlMessage = new UrlMessage();

		return urlMessage;
	}
}
