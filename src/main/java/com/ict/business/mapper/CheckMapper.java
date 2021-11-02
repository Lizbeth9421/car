package com.ict.business.mapper;

import com.ict.business.domain.Check;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/11/22:06
 */
public interface CheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    List<Check> queryaAllCheck(Check check);
}