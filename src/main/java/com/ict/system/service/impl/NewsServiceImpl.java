package com.ict.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.system.domain.News;
import com.ict.system.mapper.NewsMapper;
import com.ict.system.service.NewsService;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/0:09
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int deleteByPrimaryKey(final Integer id) {
        return this.newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(final News record) {
        return this.newsMapper.insert(record);
    }

    @Override
    public int insertSelective(final News record) {
        return this.newsMapper.insertSelective(record);
    }

    @Override
    public News selectByPrimaryKey(final Integer id) {
        return this.newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(final News record) {
        return this.newsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final News record) {
        return this.newsMapper.updateByPrimaryKey(record);
    }

    /**
     * 用于查询所有公告
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllNews(final News news) {
        final Page<Object> page = PageHelper.startPage(news.getPage(), news.getLimit());
        final List<News> list = this.newsMapper.queryAllNews(news);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据id批量删除公告
     *
     * @param ids
     */
    @Override
    public void deleteBatchNews(final Integer[] ids) {
        for (final Integer id : ids) {
            this.newsMapper.deleteByPrimaryKey(id);
        }
    }
}
