package com.sun.mapper;

import com.sun.entity.YgfCity;

public interface YgfCityMapper {
    int deleteByPrimaryKey(Long cityId);

    int insert(YgfCity record);

    int insertSelective(YgfCity record);

    YgfCity selectByPrimaryKey(Long cityId);

    int updateByPrimaryKeySelective(YgfCity record);

    int updateByPrimaryKey(YgfCity record);
}