package com.ict.business.controller;

import com.ict.business.domain.Customer;
import com.ict.business.service.CustomerService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 客户管理的控制器
 *
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/23:26
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    /**
     * 用于查询所有客户
     * 包含分页和模糊查询
     */
    @RequestMapping("/loadAllCustomer")
    public DataGridView loadAllCustomer(final Customer customer) {
        return this.service.queryAllCustomer(customer);
    }

    /**
     * 修改客户信息
     *
     * @param customer
     */
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(final Customer customer) {
        try {
            this.service.updateByPrimaryKeySelective(customer);
            return ResultObj.UPDATE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 增加客户
     *
     * @param customer
     */
    @RequestMapping("/addCustomer")
    public ResultObj addCustomer(final Customer customer) {
        try {
            customer.setCreatetime(new Date());
            this.service.insertSelective(customer);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据identity删除客户信息
     *
     * @param identity
     */
    @RequestMapping("/deleteCustomer")
    public ResultObj deleteCustomer(final String identity) {
        try {
            this.service.deleteByPrimaryKey(identity);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据identity批量删除客户信息
     *
     * @param identities
     */
    @RequestMapping("/deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(final String[] identities) {
        try {
            this.service.deleteBatchCustomer(identities);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
