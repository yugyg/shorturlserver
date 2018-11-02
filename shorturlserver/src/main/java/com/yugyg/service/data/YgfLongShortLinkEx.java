package com.yugyg.service.data;

import com.yugyg.entity.YgfLongShortLink;

/**
 * -长短链表格
 * Description: 
 * Company: www.yugyg.com
 * @author jiangchao1  
 * @date 2018年11月2日  
 * @version 1.0
 */
public class YgfLongShortLinkEx extends YgfLongShortLink {
	/**转换次数*/
	private Integer switchTimes;

	public Integer getSwitchTimes() {
		return switchTimes;
	}

	public void setSwitchTimes(Integer switchTimes) {
		this.switchTimes = switchTimes;
	}
	
}
