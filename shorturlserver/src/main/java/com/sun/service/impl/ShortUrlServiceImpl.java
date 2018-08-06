package com.sun.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.sun.entity.YgfDljSearchRecord;
import com.sun.entity.YgfLongShortLink;
import com.sun.exmapper.YgfDljExMapper;
import com.sun.mapper.YgfDljSearchRecordMapper;
import com.sun.mapper.YgfLongShortLinkMapper;
import com.sun.service.ShortUrlService;
import com.sun.service.data.IPData;
import com.sun.service.data.PieData;
import com.sun.service.data.PublicResults;
import com.sun.service.data.SearchData;
import com.sun.service.data.SevenDaysData;
import com.sun.service.data.TopThree;
import com.sun.util.Const;
import com.sun.util.HttpUtil;
import com.sun.util.TranslateLink;
import com.sun.util.Util;

/**
 * @author jiangchao1
 */
@Service("shortUrlService")
public class ShortUrlServiceImpl implements ShortUrlService{
	@Autowired
	private YgfDljSearchRecordMapper ygfDljSearchRecordMapper;
	@Autowired
	private YgfLongShortLinkMapper ygfLongShortLinkMapper;
	@Autowired
	private YgfDljExMapper ygfDljExMapper;
	
	@Transactional
	@Override
	public YgfLongShortLink insertShortLink(String longUrl) {
		YgfLongShortLink s_l_one = ygfDljExMapper.selectByLongLink(longUrl);
		if(s_l_one!=null) {
			return s_l_one;
		}else {
			YgfLongShortLink ygfLongShortLink = new YgfLongShortLink();
			ygfLongShortLink.setLonglink(longUrl);
			String shortLink = TranslateLink.shortUrl(longUrl);
			List<YgfLongShortLink> s_l_more = ygfDljExMapper.selectByShortLink(shortLink);
			if (s_l_more.size()>0) {
				String sl = s_l_more.get(0).getShortlink();
				if (s_l_more.size() == 1) {
					ygfLongShortLink.setShortlink(sl+1);
				}else {
					ygfLongShortLink.setShortlink(sl+(Long.valueOf(sl.substring(6))+1));
				}
			} else {
				ygfLongShortLink.setShortlink(shortLink);
			}
			ygfLongShortLink.setTime(new Date());
			ygfLongShortLinkMapper.insert(ygfLongShortLink);
			return ygfLongShortLink;
		}
	}
	@Override
	public void insertRecord(String ip, String userAgent, String searchShort, String resolution,String cookie,String referer) {
		YgfDljSearchRecord ygfDljSearchRecord = new YgfDljSearchRecord();
		ygfDljSearchRecord.setIp(ip);
		ygfDljSearchRecord.setBrowser(Util.getBrowser(userAgent));
		ygfDljSearchRecord.setEquipment(Util.getEquipment(userAgent));
		ygfDljSearchRecord.setSearchshort(searchShort);
		ygfDljSearchRecord.setTime(new Date());
		ygfDljSearchRecord.setCookie(cookie);
		ygfDljSearchRecord.setStatus(Util.isEmpty(userAgent)?1:0);
		ygfDljSearchRecord.setReferer(Util.isEmpty(referer)?"直接访问":referer);
		YgfLongShortLink link = ygfDljExMapper.selectShortLink(searchShort);
		ygfDljSearchRecord.setSearchlong(link == null?"no long link":link.getLonglink());
		String s = HttpUtil.getInstance().httpExecute(Const.aliIpSearch+ip);
		JSONObject data = JSONObject.parseObject(s).getJSONObject("data");
		//中国-江苏-无锡-电信
		if (data!=null) {
			ygfDljSearchRecord.setIpbelong(data.getString("country")+"-"+data.getString("region")+"-"+data.getString("city")+"-"+data.getString("isp"));
		}else {
			ygfDljSearchRecord.setIpbelong("XX-XX-XX-XX");
		}
		ygfDljSearchRecordMapper.insert(ygfDljSearchRecord);
	}
	@Override
	public String getLongUrl(String shortUrl) {
		YgfLongShortLink  entity = ygfDljExMapper.selectShortLink(shortUrl);
		return (null == entity) ? "" : entity.getLonglink();
	}
	@Override
	public PublicResults selectSevenDaysData(SearchData searchData) {
		SevenDaysData sevenDaysData = ygfDljExMapper.selectSevenDaysData(searchData);
		PublicResults  temp = new PublicResults();
		temp.setxAxis(sevenDaysData.getxData());
		String[] series = {sevenDaysData.getTotal(),sevenDaysData.getUv(),sevenDaysData.getOklink(),sevenDaysData.getErrorlink(),sevenDaysData.getIpnum()};
		temp.setSeries(series);
		return temp;
	}
	@Override
	public PublicResults selectTwentyHours(SearchData searchData) {
		SevenDaysData twentyHours = ygfDljExMapper.selectTwentyHours(searchData);
		PublicResults  temp = new PublicResults();
		temp.setxAxis(twentyHours.getxData());
		String[] series = {twentyHours.getTotal(),twentyHours.getUv(),twentyHours.getOklink(),twentyHours.getErrorlink(),twentyHours.getIpnum()};
		temp.setSeries(series);
		return temp;
	}
	@Override
	public List<PieData> selectEquitMent(SearchData searchData) {
		return ygfDljExMapper.selectEquitMent(searchData);
	}
	@Override
	public PublicResults selectBrowser(SearchData searchData) {
		return ygfDljExMapper.selectBrowser(searchData);
	}
	@Override
	public PublicResults selectInternet(SearchData searchData) {
		return ygfDljExMapper.selectInternet(searchData);
	}
	@Override
	public TopThree topThree(SearchData searchData) {
		return ygfDljExMapper.topThree(searchData);
	}
	@Override
	public List<PieData> selectResolution(SearchData searchData) {
		return ygfDljExMapper.selectResolution(searchData);
	}
	@Override
	public List<YgfDljSearchRecord> selectAllRecord(SearchData searchData) {
		return ygfDljExMapper.selectAllRecord(searchData);
	}
	@Override
	public List<IPData> selectIps(SearchData searchData) {
		return ygfDljExMapper.selectIps(searchData);
	}
	@Override
	public List<PieData> selectPrivonce(SearchData searchData) {
		return ygfDljExMapper.selectPrivonce(searchData);
	}
	@Override
	public List<PieData> selectCity(SearchData searchData) {
		return ygfDljExMapper.selectCity(searchData);
	}
	@Override
	public Integer selectAllRecordCount(SearchData searchData) {
		return ygfDljExMapper.selectAllRecordCount(searchData);
	}
}
