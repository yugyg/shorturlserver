package com.yugyg.mapper;

import com.yugyg.entity.YgfCalendar;

public interface YgfCalendarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfCalendar record);

    int insertSelective(YgfCalendar record);

    YgfCalendar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfCalendar record);

    int updateByPrimaryKey(YgfCalendar record);
}