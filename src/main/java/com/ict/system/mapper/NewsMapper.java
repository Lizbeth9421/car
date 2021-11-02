package com.ict.system.mapper;

import com.ict.system.domain.News;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/0:09
 */
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    /**
     * 用于查询所有公告
     * 包含分页和模糊查询
     */
    List<News> queryAllNews(News news);
}