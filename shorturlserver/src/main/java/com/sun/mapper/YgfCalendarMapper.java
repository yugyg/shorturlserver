package com.sun.mapper;

import com.sun.entity.YgfCalendar;

public interface YgfCalendarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfCalendar record);

    int insertSelective(YgfCalendar record);

    YgfCalendar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfCalendar record);

    int updateByPrimaryKey(YgfCalendar record);
}