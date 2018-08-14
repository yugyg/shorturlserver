 package com.yugyg.service.data;

 /**
  * 公共键值对结果集
  * @author jiangchao1
  *
  */
 public class PieData {
	 //名称
	 private String name;
	 //比例
	 private Double value;
	 //额外字段
	 private String extra;
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
