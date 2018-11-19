package com.yugyg.mapper;

import com.yugyg.entity.YgfDljSearchRecord;

public interface YgfDljSearchRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YgfDljSearchRecord record);

    int insertSelective(YgfDljSearchRecord record);

    YgfDljSearchRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YgfDljSearchRecord record);

    int updateByPrimaryKeyWithBLOBs(YgfDljSearchRecord record);

    int updateByPrimaryKey(YgfDljSearchRecord record);
}