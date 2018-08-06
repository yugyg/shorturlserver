package com.sun.exmapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.entity.YgfLongShortLink;
import com.sun.service.data.PieData;
import com.sun.service.data.PublicResults;
import com.sun.service.data.SearchData;
import com.sun.service.data.SevenDaysData;
import com.sun.service.data.TopThree;

@Repository("ygfDljExMapper")
public interface YgfDljExMapper {
	/**
	 * 短链接找长连接
	 * @param shortLink
	 * @return
	 */
	YgfLongShortLink selectShortLink(String shortLink);
	/**
	 * 短链接找长连接（like）
	 * @param shortLink
	 * @return
	 */
	List<YgfLongShortLink> selectByShortLink(String shortLink);
	/**
	 * 长链接找短连接
	 * @param shortLink
	 * @return
	 */
	YgfLongShortLink selectByLongLink(String longLink);
	/**
	 * 前七天数据统计查询
	 * @param searchData
	 * @return
	 */
	SevenDaysData selectSevenDaysData(SearchData searchData);
	
	/**
	 * 
	 * @param searchData
	 * @return
	 */
	SevenDaysData selectTwentyHours(SearchData searchData);
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