package com.yugyg.mapper;

import com.yugyg.entity.YgfProvince;

public interface YgfProvinceMapper {
    int deleteByPrimaryKey(Long provinceId);

    int insert(YgfProvince record);

    int insertSelective(YgfProvince record);

    YgfProvince selectByPrimaryKey(Long provinceId);

    int updateByPrimaryKeySelective(YgfProvince record);

    int updateByPrimaryKey(YgfProvince record);
}