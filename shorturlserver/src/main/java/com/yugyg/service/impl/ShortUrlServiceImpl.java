package com.yugyg.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yugyg.entity.YgfDljSearchRecord;
import com.yugyg.entity.YgfLongShortLink;
import com.yugyg.entity.YgfProvince;
import com.yugyg.exmapper.YgfDljExMapper;
import com.yugyg.mapper.YgfDljSearchRecordMapper;
import com.yugyg.mapper.YgfLongShortLinkMapper;
import com.yugyg.service.ShortUrlService;
import com.yugyg.service.data.IPData;
import com.yugyg.service.data.PieData;
import com.yugyg.service.data.PublicResults;
import com.yugyg.service.data.SearchData;
import com.yugyg.service.data.SevenDaysData;
import com.yugyg.service.data.TopThree;
import com.yugyg.util.Const;
import com.yugyg.util.HttpUtil;
import com.yugyg.util.TranslateLink;
import com.yugyg.util.Util;

import sun.util.logging.resources.logging;

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
		ygfDljSearchRecord.setReferer(Util.isEmpty(referer)?"直接访问":referer);
		ygfDljSearchRecord.setUseragent(userAgent);
		YgfLongShortLink link = ygfDljExMapper.selectShortLink(searchShort);
		String lu = link == null?"no long link":link.getLonglink();//.replaceAll("(&|.?)phoneNum=[0-9]*", ""
		Pattern pattern = Pattern.compile("(&|.?)ygfPhoneNum=[0-9]*");//匹配手机号
		Matcher matcher = pattern.matcher(lu);
		while (matcher.find()) {
			ygfDljSearchRecord.setPhonenum(matcher.group().substring(13));
		}
		ygfDljSearchRecord.setSearchlong(lu.replaceAll("(&|.?)ygfPhoneNum=[0-9]*", ""));
		String ipBelong = Util.getIpBelong(ip);
		ygfDljSearchRecord.setStatus((Util.isEmpty(userAgent)||(Util.isEmpty(ip)))?1:0);
		//江苏省无锡市-电信
		ygfDljSearchRecord.setIpbelong(ipBelong);
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
	@Override
	public List<YgfProvince> getAllProvince() {
		return ygfDljExMapper.getAllProvince();
	}
}
