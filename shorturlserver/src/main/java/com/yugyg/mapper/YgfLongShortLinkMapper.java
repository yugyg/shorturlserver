package com.yugyg.mapper;

import com.yugyg.entity.YgfLongShortLink;

public interface YgfLongShortLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int insert(YgfLongShortLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int insertSelective(YgfLongShortLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    YgfLongShortLink selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(YgfLongShortLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(YgfLongShortLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ygf_long_short_link
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(YgfLongShortLink record);
}