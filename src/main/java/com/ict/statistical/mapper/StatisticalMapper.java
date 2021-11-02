package com.ict.statistical.mapper;

import com.ict.statistical.domain.BaseEntity;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/16/15:24
 */
public interface StatisticalMapper {

    /*
     * 查询客户地区数据
     */
    List<BaseEntity> queryCustomerAreaStat();

    /**
     * 查询业务员年度销售额数据
     */
    List<BaseEntity> queryOpernameYearGradeStat(String year);

    /**
     * 查询业务员年度销售额数据
     */
    List<Double> queryComponyYearGradeStat(String year);
}
