package com.sun.service.data;

import java.util.List;

/**
 * 公共结果集
 * @Description:  
 * @author jiangchao1   
 * @date 2018-7-26 上午11:38:46 
 * @version V1.0
 */
public class PublicResults {
	//x坐标
	private String xAxis;
	//y坐标
	private String yAxis;
	//饼图专用
	private String legendData;
	//{"320, 302, 301, 334, 390, 330, 320","320, 302, 301, 334, 390, 330, 320"}
	private String[] series;
	//str
	private String seriesStr;
	//是否是名值集合
	private Integer isNameValue;
	{isNameValue = 0;}
	//名值集合
	private List<PieData> pieData;
	public String getxAxis() {
		return xAxis;
	}
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	public String getyAxis() {
		return yAxis;
	}
	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
	public String[] getSeries() {
		return series;
	}
	public void setSeries(String[] series) {
		this.series = series;
	}
	public String getSeriesStr() {
		return seriesStr;
	}
	public void setSeriesStr(String seriesStr) {
		this.seriesStr = seriesStr;
	}
	public Integer getIsNameValue() {
		return isNameValue;
	}
	public void setIsNameValue(Integer isNameValue) {
		this.isNameValue = isNameValue;
	}
	public List<PieData> getPieData() {
		return pieData;
	}
	public void setPieData(List<PieData> pieData) {
		this.pieData = pieData;
	}
	public String getLegendData() {
		return legendData;
	}
	public void setLegendData(String legendData) {
		this.legendData = legendData;
	}
	
}
