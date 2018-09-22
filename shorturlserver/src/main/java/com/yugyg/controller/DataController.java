package com.yugyg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.yugyg.entity.YgfDljSearchRecord;
import com.yugyg.entity.YgfProvince;
import com.yugyg.mapper.YgfDljSearchRecordMapper;
import com.yugyg.service.ShortUrlService;
import com.yugyg.service.data.IPData;
import com.yugyg.service.data.PieData;
import com.yugyg.service.data.PublicResults;
import com.yugyg.service.data.ResultEntity;
import com.yugyg.service.data.SearchData;
import com.yugyg.service.data.TopThree;
import com.yugyg.util.Const;
import com.yugyg.util.Util;

/**
 * 数据统计
 * @author jiangchao1
 *
 */
@RestController
@CrossOrigin
public class DataController {
	@Resource
	private ShortUrlService shortUrlService;
	@Resource
	private YgfDljSearchRecordMapper ygfDljSearchRecordMapper;
	private static Logger logger = LoggerFactory.getLogger(DataController.class);
	/**
	 * 短链数据统计
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @param shortUrl  短链
	 * @param witch		
	 * @param browser	浏览器名称
	 * @param equipment	设备名称
	 * @param country  	国家
	 * @param internet	网络供应商
	 * @param status 链接状态
	 * @param privonce	省	
	 * @param page		当前页
	 * @param httpRequest
	 * @param webRequest
	 * @param httpResponse
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/data")
	public ResultEntity shortToLongUrl(
		@RequestParam(required = false) String startTime,
		@RequestParam(required = false) String endTime,
		@RequestParam(required = true) String shortUrl,
		@RequestParam(required = false ,defaultValue = "") String witch,
		@RequestParam(required = false) String browser,
		@RequestParam(required = false) String equipment,
		@RequestParam(required = false) String country,
		@RequestParam(required = false) String internet,
		@RequestParam(required = false) String status,
		@RequestParam(required = false) String privonce,
		@RequestParam(required = false) String page,
	HttpServletRequest httpRequest,
	WebRequest webRequest,HttpServletResponse httpResponse) throws ServletException, IOException {
		ResultEntity result = new ResultEntity();
		Map<String, Object> map = new HashMap<>();
		if (!Util.isEmpty(shortUrl)) {
			result.setResult(Const.result_success);
			SearchData searchData = new SearchData();
			if (!Util.isEmpty(startTime)) {
				searchData.setBegin(startTime);
			}
			searchData.setEnd(Util.isEmpty(endTime)?Util.dateFormat(new Date(),Const.date_formate_1):endTime);
			searchData.setShortUrl(shortUrl);
			String longU = shortUrlService.getLongUrl(shortUrl);
			searchData.setLongUrl(Util.isEmpty(longU)?"":longU.replaceAll("(&|.?)ygfPhoneNum=[0-9]*", ""));
			if (!Util.isEmpty(country)) {
				searchData.setCountry(country);
			}
			if (!Util.isEmpty(browser)) {
				searchData.setBrowser(browser);
			}
			if (!Util.isEmpty(equipment)) {
				searchData.setEquipment(equipment);
			}
			if (!Util.isEmpty(internet)) {
				searchData.setInternet(internet);
			}
			if (!Util.isEmpty(status)) {
				searchData.setStatus(status);
			}
			switch (witch) {
			case "A"://最近七天加今天
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
				map.put("sevenDaysData", sevenDaysData);
				map.put("tt", tt);//左边七天数据
				break;
			case "B"://24小时
				PublicResults twentyHours = shortUrlService.selectTwentyHours(searchData);
				map.put("twentyHours", twentyHours);
				break;
			case "C"://设备
				List<PieData> equit = shortUrlService.selectEquitMent(searchData);
				map.put("equit", equit);
				break;
			case "D":
				PublicResults brow = shortUrlService.selectBrowser(searchData);
				map.put("brow", brow);
				break;
			case "E"://地图省
				List<PieData> privon = shortUrlService.selectPrivonce(searchData);
				map.put("privon", privon);
				break;
			case "EC"://地图市
				searchData.setProvince(privonce);
				List<PieData> city = shortUrlService.selectCity(searchData);
				map.put("city", city);
				break;
			case "F"://供应商
				PublicResults inter = shortUrlService.selectInternet(searchData);
				map.put("inter", inter);
				break;
			case "G"://分辨率
				List<PieData> resolution = shortUrlService.selectResolution(searchData);
				map.put("resolution", resolution);
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
				map.put("allRecord", allRecord);
				map.put("totalPage", count%50==0?count/50:(count/50)+1);
				break;
			case "I"://访问记录明细
				List<IPData> ips = shortUrlService.selectIps(searchData);
				map.put("ips", ips);
				break;
			default:
				TopThree topThree = shortUrlService.topThree(searchData);
				List<YgfProvince> allProvince= shortUrlService.getAllProvince();
				map.put("topThree", topThree);
				map.put("allProvince", allProvince);
				break;
			}
		}else {
			result.setResult(Const.result_fail);
			result.setDesc("短链参数为空");
		}
		result.setData(map);
		return result;
	}
	/*@GetMapping("/aaa")
	public List<YgfDljSearchRecord> getProvinces() {
		SearchData searchData = new SearchData();
		searchData.setEnd(Util.dateFormat(new Date(),Const.date_formate_1));
		List<YgfDljSearchRecord> list = shortUrlService.selectAllRecord(new SearchData());
		for (int i = 0; i < list.size(); i++) {
			YgfDljSearchRecord e= list.get(i);
			e.setIpbelong(Util.getIpBelong(e.getIp()));
			ygfDljSearchRecordMapper.updateByPrimaryKey(e);
		}
		return list;
	}*/
}
