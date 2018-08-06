package com.sun.service;

import java.util.List;

import com.sun.entity.YgfLongShortLink;
import com.sun.service.data.PieData;
import com.sun.service.data.PublicResults;
import com.sun.service.data.SearchData;
import com.sun.service.data.SevenDaysData;
import com.sun.service.data.TopThree;

/**
 * @author jiangchao1
 */
public interface ShortUrlService {
	/**
	 * 压缩保存长连接
	 * @param longUrl
	 * @return
	 */
	YgfLongShortLink insertShortLink(String longUrl);
	/**
	 * 记录查询信息
	 * 返回长连接
	 * @param ip   ip
	 * @param userAgent   请求头
	 * @param searchShort  访问短地址
	 * @param resolution  分辨率
	 * @return
	 */
	void insertRecord(String ip,String userAgent,String searchShort,String resolution,String cookie,String referer);
	/**
	 * 获取长链接
	 * @param shortUrl
	 * @return
	 */
	String getLongUrl(String shortUrl);
	/**
	 * 前七天数据统计查询
	 * @param searchData
	 * @return
	 */
	PublicResults selectSevenDaysData(SearchData searchData);
	
	/**
	 * 
	 * @param searchData
	 * @return
	 */
	PublicResults selectTwentyHours(SearchData searchData);
	/**
	 * 设配饼图
	 * @param searchData
	 * @return
	 */
	List<PieData> selectEquitMent(SearchData searchData);
	/**
	 * 查询浏览器
	 * @param searchData
	 * @return
	 */
	PublicResults selectBrowser(SearchData searchData);
	/**
	 * 供应商
	 * @param searchData
	 * @return
	 */
	PublicResults selectInternet(SearchData searchData);
	/**
	 * 顶部三个数据
	 * @param searchData
	 * @return
	 */
	TopThree topThree(SearchData searchData);
}
