package com.ict.business.controller;

import com.ict.business.domain.Check;
import com.ict.business.domain.Rent;
import com.ict.business.service.CheckService;
import com.ict.business.service.RentService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/12/23:15
 */
@RestController
@RequestMapping("/check")
public class checkController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;

    /**
     * 判断输入的出租单号是否存在
     *
     * @param rentid
     * @return
     */
    @RequestMapping("/checkRentExist")
    public Rent checkRentExist(final String rentid) {
        return this.rentService.selectByPrimaryKey(rentid);
    }

    /**
     * 根据出租单号加载表单和卡片面板的数据
     *
     * @param rentid
     * @return
     */
    @RequestMapping("/initCheckFormData")
    public Map<String, Object> initCheckFormData(final String rentid) {
        return this.checkService.initCheckFormData(rentid);
    }


    /**
     * 添加检查单信息
     *
     * @param check
     * @return
     */
    @RequestMapping("/addCheck")
    public ResultObj addCheck(final Check check) {
        try {
            check.setCreatetime(new Date());
            this.checkService.addCheck(check);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载检查单管理的数据
     *
     * @param check
     * @return
     */
    @RequestMapping("/loadAllCheck")
    public DataGridView loadAllCheck(final Check check) {
        return this.checkService.queryAllCheck(check);
    }

    /**
     * 更新检察管理单的数据
     *
     * @param check
     * @return
     */
    @RequestMapping("/updateCheck")
    public ResultObj updateCheck(final Check check) {
        try {
            this.checkService.updateByPrimaryKeySelective(check);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

}
