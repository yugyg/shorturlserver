package com.yugyg.mapper;

import com.yugyg.entity.YgfHours;

public interface YgfHoursMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfHours record);

    int insertSelective(YgfHours record);

    YgfHours selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfHours record);

    int updateByPrimaryKey(YgfHours record);
}