package com.ict.statistical.service.impl;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/16/15:24
 */

import com.ict.statistical.domain.BaseEntity;
import com.ict.statistical.mapper.StatisticalMapper;
import com.ict.statistical.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private StatisticalMapper statisticalMapper;

    /*
     * 查询客户地区数据
     */
    @Override
    public List<BaseEntity> loadCustomerAreaStatistical() {
        return this.statisticalMapper.queryCustomerAreaStat();
    }

    /**
     * 加载业务员年度销售额数据
     *
     * @param year
     * @return
     */
    @Override
    public List<BaseEntity> loadOpernameYearGradeStat(final String year) {
        return this.statisticalMapper.queryOpernameYearGradeStat(year);
    }

    /**
     * 加载公司年度销售额数据
     *
     * @param year
     * @return
     */
    @Override
    public List<Double> loadComponyYearGradeStat(final String year) {
        return this.statisticalMapper.queryComponyYearGradeStat(year);
    }
}
