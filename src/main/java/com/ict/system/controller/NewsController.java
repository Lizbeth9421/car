package com.ict.system.controller;

import com.ict.system.constant.constant;
import com.ict.system.domain.News;
import com.ict.system.domain.SysUser;
import com.ict.system.service.NewsService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import com.ict.system.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/0:25
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService service;

    @RequestMapping("/toNewsManger")
    public String toNewsManger() {
        return "/system/newsManger";
    }

    @ResponseBody
    @RequestMapping("/loadAllNews")
    public DataGridView loadAllNews(final News news) {
        return this.service.queryAllNews(news);
    }

    /**
     * 根据id删除公告
     *
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteNews")
    public ResultObj deleteNews(final Integer id) {
        try {
            this.service.deleteByPrimaryKey(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id批量删除公告
     *
     * @param ids
     */
    @ResponseBody
    @RequestMapping("/deleteBatchNews")
    public ResultObj deleteBatchNews(final Integer[] ids) {
        try {
            this.service.deleteBatchNews(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @ResponseBody
    @RequestMapping("/addNews")
    public ResultObj addNews(final News news) {
        try {
            news.setCreatetime(new Date());
            final SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute(constant.USER_IN_SESSION);
            news.setOpername(user.getRealname());
            this.service.insertSelective(news);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @ResponseBody
    @RequestMapping("/updateNews")
    public ResultObj updateNews(final News news) {
        try {
            this.service.updateByPrimaryKeySelective(news);
            return ResultObj.UPDATE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @ResponseBody
    @RequestMapping("/loadNewsById")
    public News loadNewsById(final Integer id) {
        return this.service.selectByPrimaryKey(id);
    }
}
