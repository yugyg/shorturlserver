package com.sun.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSONObject;
import com.sun.entity.YgfDljSearchRecord;
import com.sun.service.ShortUrlService;
import com.sun.service.data.IPData;
import com.sun.service.data.PieData;
import com.sun.service.data.PublicResults;
import com.sun.service.data.SearchData;
import com.sun.service.data.TopThree;
import com.sun.util.Const;
import com.sun.util.Util;

@RestController
public class DataController {
	@Resource
	private ShortUrlService shortUrlService;
	
	@GetMapping("/data")
	public JSONObject shortToLongUrl(@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime,
		@RequestParam(required = true) String shortUrl,
		@RequestParam(required = false) String witch,
		@RequestParam(required = false) String browser,
		@RequestParam(required = false) String equipment,
		@RequestParam(required = false) String country,
		@RequestParam(required = false) String internet,
		@RequestParam(required = false) String status,
		@RequestParam(required = false) String privonce,
		@RequestParam(required = false) String page,
	HttpServletRequest httpRequest,
	WebRequest webRequest,HttpServletResponse httpResponse) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		if (!Util.isEmpty(shortUrl)) {
			json.put("result", "success");
			SearchData searchData = new SearchData();
			searchData.setBegin(startTime);
			searchData.setEnd(Util.isEmpty(startTime)?Util.dateFormat(new Date(),Const.cookieFormate):startTime);
			searchData.setShortUrl(shortUrl);
			searchData.setCountry(country);
			searchData.setBrowser(browser);
			searchData.setEquipment(equipment);
			searchData.setInternet(internet);
			searchData.setStatus(status);
			switch (witch) {
			case "A":
				PublicResults sevenDaysData = shortUrlService.selectSevenDaysData(searchData);
				String [] series = sevenDaysData.getSeries();
				String[] total = series[0].split(",");
				String[] ip = series[4].split(",");
				String[] uv = series[1].split(",");
				Integer t = 0;
				Integer p = 0;
				Integer u = 0;
				for (int i = 1; i < total.length; i++) {
					t += Integer.valueOf(total[i]);
					p += Integer.valueOf(ip[i]);
					u += Integer.valueOf(uv[i]);
				}
				TopThree tt = new TopThree();
				tt.setIp(p);
				tt.setTotal(t);
				tt.setUv(u);
				json.put("sevenDaysData", sevenDaysData);
				json.put("tt", tt);
				break;
			case "B":
				PublicResults twentyHours = shortUrlService.selectTwentyHours(searchData);
				json.put("twentyHours", twentyHours);
				break;
			case "C":
				List<PieData> equit = shortUrlService.selectEquitMent(searchData);
				json.put("equit", equit);
				break;
			case "D":
				PublicResults brow = shortUrlService.selectBrowser(searchData);
				json.put("brow", brow);
				break;
			case "EP"://地图省
				List<PieData> privon = shortUrlService.selectPrivonce(searchData);
				json.put("privonce", privon);
				break;
			case "EC"://地图市
				searchData.setProvince(privonce);
				List<PieData> city = shortUrlService.selectCity(searchData);
				json.put("city", city);
				break;
			case "F"://供应商
				PublicResults inter = shortUrlService.selectInternet(searchData);
				json.put("inter", inter);
				break;
			case "G"://分辨率
				List<PieData> resolution = shortUrlService.selectResolution(searchData);
				json.put("resolution", resolution);
				break;
			case "H"://访问记录明细
				Integer count = shortUrlService.selectAllRecordCount(searchData);
				if (!Util.isEmpty(page)) {
					if (Integer.valueOf(page)<=0) {
						searchData.setStartIndex(0);
					}else if(Integer.valueOf(page)>count){
						searchData.setStartIndex((count-1)*50);
					}else {
						searchData.setStartIndex((Integer.parseInt(page)-1)*50);
					}
				}
				List<YgfDljSearchRecord> allRecord = shortUrlService.selectAllRecord(searchData);
				json.put("allRecord", allRecord);
				json.put("totalPage", count%50==0?count/50:(count/50)+1);
				break;
			case "I"://访问记录明细
				List<IPData> ips = shortUrlService.selectIps(searchData);
				json.put("ips", ips);
				break;
			default:
				TopThree topThree = shortUrlService.topThree(searchData);
				json.put("topThree", topThree);
				break;
			}
		}else {
			json.put("result", "fail");
		}
		return json;
	}
}
