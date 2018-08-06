package com.sun.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.sun.service.ShortUrlService;
import com.sun.service.data.SearchData;

@RestController
public class DataController {
	@Resource
	private ShortUrlService shortUrlService;
	
	@GetMapping("/data")
	public void shortToLongUrl(@RequestParam String startTime,@RequestParam String endTime,
	@RequestParam String witch, HttpServletRequest httpRequest,
	@RequestParam(required = true) String shortUrl,
	@RequestParam String browser,
	@RequestParam String equipment,
	@RequestParam String country,
	@RequestParam String internet,
	@RequestParam String status,
	WebRequest webRequest,HttpServletResponse httpResponse) throws ServletException, IOException {
		SearchData searchData = new SearchData();
		searchData.setBegin(startTime);
		searchData.setEnd(endTime);
		searchData.setShortUrl(shortUrl);
		searchData.setCountry(country);
		searchData.setBrowser(browser);
		searchData.setEquipment(equipment);
		searchData.setInternet(internet);
		searchData.setStatus(status);
		switch (witch) {
		case "A":
			
			break;
		case "B":
			
			break;
		case "C":
			
			break;
		case "D":
			
			break;
		case "E":
			
			break;

		default:
			break;
		}
	}
}
