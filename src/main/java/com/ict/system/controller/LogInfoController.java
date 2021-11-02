package com.ict.system.controller;

import com.ict.system.domain.LogInfo;
import com.ict.system.service.LogInfoService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/04/23:46
 */
@Controller
@RequestMapping("/logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService service;

    @RequestMapping("/toLogInfoManger")
    public String toLogInfoManger() {
        return "/system/logInfoManger";
    }


    /**
     * 用于查询所有登录日志信息
     * 包含分页和模糊查询
     */
    @ResponseBody
    @RequestMapping("/loadAllLogInfo")
    public DataGridView loadAllLogInfo(final LogInfo info) {
        return this.service.queryAllLogInfo(info);
    }

    /**
     * 根据id删除日志信息
     *
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteLogInfo")
    public ResultObj deleteLogInfo(final Integer id) {
        try {
            this.service.deleteByPrimaryKey(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志信息
     *
     * @param ids 多个id组成的数组
     */
    @ResponseBody
    @RequestMapping("/deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(final Integer[] ids) {
        try {
            this.service.deleteBatchLogInfo(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
