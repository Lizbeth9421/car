package com.ict.system.service;

import com.ict.system.domain.LogInfo;
import com.ict.system.util.DataGridView;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/04/21:45
 */
public interface LogInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);


    /**
     * 用于查询所有登录日志信息
     * 包含分页和模糊查询
     */
    DataGridView queryAllLogInfo(LogInfo logInfo);

    /**
     * 批量删除日志信息
     *
     * @param ids 多个id组成的数组
     */
    void deleteBatchLogInfo(Integer[] ids);
}
