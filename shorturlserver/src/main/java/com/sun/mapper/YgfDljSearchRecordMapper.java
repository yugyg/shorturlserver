package com.sun.mapper;

import com.sun.entity.YgfDljSearchRecord;

public interface YgfDljSearchRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfDljSearchRecord record);

    int insertSelective(YgfDljSearchRecord record);

    YgfDljSearchRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfDljSearchRecord record);

    int updateByPrimaryKey(YgfDljSearchRecord record);
}