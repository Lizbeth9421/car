package com.ict.business.service;

import com.ict.business.domain.Check;
import com.ict.system.util.DataGridView;

import java.util.Map;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/11/22:06
 */
public interface CheckService {


    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    void addCheck(Check check);

    /**
     * 根据出租单号加载表单和卡片面板的数据
     *
     * @param rentid
     * @return
     */
    Map<String, Object> initCheckFormData(String rentid);

    /**
     * 用于查询所有检查单信息
     * 包含分页和模糊查询
     */
    DataGridView queryAllCheck(Check check);
}
