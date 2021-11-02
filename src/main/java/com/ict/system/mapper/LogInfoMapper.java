package com.ict.system.mapper;

import com.ict.system.domain.LogInfo;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/04/21:45
 */
public interface LogInfoMapper {
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
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);
}