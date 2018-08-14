package com.sun.mapper;

import com.sun.entity.YgfLongShortLink;

public interface YgfLongShortLinkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfLongShortLink record);

    int insertSelective(YgfLongShortLink record);

    YgfLongShortLink selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfLongShortLink record);

    int updateByPrimaryKeyWithBLOBs(YgfLongShortLink record);

    int updateByPrimaryKey(YgfLongShortLink record);
}