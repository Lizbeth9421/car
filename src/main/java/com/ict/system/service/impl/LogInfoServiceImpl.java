package com.ict.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ict.system.domain.LogInfo;
import com.ict.system.mapper.LogInfoMapper;
import com.ict.system.service.LogInfoService;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/04/21:45
 */
@Service
@Transactional
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public int deleteByPrimaryKey(final Integer id) {
        return this.logInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(final LogInfo record) {
        return this.logInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(final LogInfo record) {
        return this.logInfoMapper.insertSelective(record);
    }

    @Override
    public LogInfo selectByPrimaryKey(final Integer id) {
        return this.logInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(final LogInfo record) {
        return this.logInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final LogInfo record) {
        return this.logInfoMapper.updateByPrimaryKey(record);
    }


    /**
     * 用于查询所有登录日志信息
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllLogInfo(final LogInfo logInfo) {
        final Page<Object> page = PageHelper.startPage(logInfo.getPage(), logInfo.getLimit());
        final List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfo);
        PageInfo<LogInfo> pageInfo = new PageInfo<>(data);
        return new DataGridView(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 批量删除日志信息
     *
     * @param ids 多个id组成的数组
     */
    @Override
    public void deleteBatchLogInfo(final Integer[] ids) {
        for (final Integer id : ids) {
            this.logInfoMapper.deleteByPrimaryKey(id);
        }
    }

}
