package com.ict.system.service;

import com.ict.system.domain.News;
import com.ict.system.util.DataGridView;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/0:09
 */
public interface NewsService {


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
    DataGridView queryAllNews(News news);


    /**
     * 根据id批量删除公告
     *
     * @param ids
     */
    void deleteBatchNews(Integer[] ids);
}
