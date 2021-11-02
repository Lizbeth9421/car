package com.ict.statistical.service;

import com.ict.statistical.domain.BaseEntity;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/16/15:24
 */
public interface StatisticalService {

    /*
     * 查询客户地区数据
     */
    List<BaseEntity> loadCustomerAreaStatistical();

    /**
     * 加载业务员年度销售额数据
     *
     * @param year
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStat(String year);

    /**
     * 加载公司年度销售额数据
     *
     * @param year
     * @return
     */
    List<Double> loadComponyYearGradeStat(String year);

}
