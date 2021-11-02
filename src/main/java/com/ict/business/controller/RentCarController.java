package com.ict.business.controller;

import com.ict.business.domain.Rent;
import com.ict.business.service.CustomerService;
import com.ict.business.service.RentService;
import com.ict.system.constant.constant;
import com.ict.system.domain.SysUser;
import com.ict.system.util.DataGridView;
import com.ict.system.util.RandomUtils;
import com.ict.system.util.ResultObj;
import com.ict.system.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 汽车出租的控制器
 *
 * @Author: Lizbeth9421
 * @Date: 2021/08/09/23:40
 */
@RestController
@RequestMapping("/rent")
public class RentCarController {
    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;

    /**
     * 判断该身份证账号是否存在
     *
     * @param rent
     * @return
     */
    @RequestMapping("/checkCustomerExist")
    public ResultObj checkCustomerExist(final Rent rent) {
        if (this.customerService.selectByPrimaryKey(rent.getIdentity()) != null) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 返回用于初始化点击出租汽车后的弹出层的数据
     *
     * @param rent
     * @return
     */
    @RequestMapping("/initRentForm")
    public Rent initRentForm(final Rent rent) {
        //生成出租单号
        rent.setRentid(RandomUtils.createRandomStringUseTime(constant.CAR_ORDER_CZ));
        //设置起租时间
        rent.setBegindate(new Date());
        //设置租借人
        final SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute(constant.USER_IN_SESSION);
        rent.setOpername(user.getRealname());
        return rent;
    }

    /**
     * 用于处理汽车出租中的出租汽车
     *
     * @param rent
     * @return
     */
    @RequestMapping("/saveRentCar")
    public ResultObj saveRentCar(final Rent rent) {
        try {
            //设置创建时间
            rent.setCreatetime(new Date());
            //设置归还状态
            rent.setRentflag(constant.RENT_BACK_FALSE);
            this.rentService.insertSelective(rent);
            return ResultObj.RENT_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.RENT_ERROR;
        }
    }

    /**
     * 用于加载出租单管理中的数据
     *
     * @param rent
     * @return
     */
    @RequestMapping("/loadAllRent")
    public DataGridView loadAllRent(final Rent rent) {
        return this.rentService.queryAllRent(rent);
    }

    /**
     * 用于加载出租单管理中的数据
     *
     * @param rent
     * @return
     */
    @RequestMapping("/updateRent")
    public ResultObj updateRent(final Rent rent) {
        try {
            this.rentService.updateByPrimaryKeySelective(rent);
            return ResultObj.UPDATE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除该出租单号对应的出租单
     *
     * @param rentid
     * @return
     */
    @RequestMapping("/deleteRent")
    public ResultObj deleteRent(final String rentid) {
        try {
            this.rentService.deleteByPrimaryKey(rentid);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
