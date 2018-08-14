package com.yugyg.exmapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yugyg.entity.YgfCity;
import com.yugyg.entity.YgfDljSearchRecord;
import com.yugyg.entity.YgfLongShortLink;
import com.yugyg.entity.YgfProvince;
import com.yugyg.service.data.IPData;
import com.yugyg.service.data.PieData;
import com.yugyg.service.data.PublicResults;
import com.yugyg.service.data.SearchData;
import com.yugyg.service.data.SevenDaysData;
import com.yugyg.service.data.TopThree;

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
	 * 获得所有市
	 * @return
	 */
	List<YgfCity> getAllCity();
}