package com.yugyg.service;

import java.util.List;

import com.yugyg.entity.YgfDljSearchRecord;
import com.yugyg.entity.YgfLongShortLink;
import com.yugyg.entity.YgfProvince;
import com.yugyg.service.data.IPData;
import com.yugyg.service.data.PieData;
import com.yugyg.service.data.PublicResults;
import com.yugyg.service.data.ResultEntity;
import com.yugyg.service.data.SearchData;
import com.yugyg.service.data.TopThree;

/**
 * @author jiangchao1
 */
public interface ShortUrlService {
	/**
	 * 压缩保存长连接
	 * @param longUrl
	 * @param desc 
	 * @return
	 */
	YgfLongShortLink insertShortLink(String longUrl, String desc);
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
	 * 24小时
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
	 * 分辨率
	 * @param searchData
	 * @return
	 */
	List<PieData> selectResolution(SearchData searchData);
	/**
	 * 全部数据
	 * @param searchData
	 * @return
	 */
	List<YgfDljSearchRecord> selectAllRecord(SearchData searchData);
	/**
	 * 查询总条数
	 * @param searchData
	 * @return
	 */
	Integer selectAllRecordCount(SearchData searchData);
	/**
	 * ip数据
	 * @param searchData
	 * @return
	 */
	List<IPData> selectIps(SearchData searchData);
	/**
	 * 顶部三个数据
	 * @param searchData
	 * @return
	 */
	TopThree topThree(SearchData searchData);
	/**
	 * 地图省信息
	 * @param searchData
	 * @return
	 */
	List<PieData> selectPrivonce(SearchData searchData);
	/**
	 * 地图市信息
	 * @param searchData
	 * @return
	 */
	List<PieData> selectCity(SearchData searchData);
	/**
	 * 获得所有省份
	 * @param searchData
	 * @return
	 */
	List<YgfProvince> getAllProvince();
	/**
	 * -查询长短链表格
	 * Description:  
	 * @author jiangchao1  
	 * @date 2018年11月2日  
	 * @version 1.0
	 * @param currentPage 
	 */
	ResultEntity getAllLink(String shortUrl, String longUrl, String desc, String start, String end, String page);
}
