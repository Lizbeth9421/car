package com.ict.business.service;

import com.ict.business.domain.Customer;
import com.ict.system.util.DataGridView;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/22:35
 */
public interface CustomerService {


    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 用于查询所有客户
     * 包含分页和模糊查询
     */
    DataGridView queryAllCustomer(Customer customer);


    /**
     * 根据身份证号批量删除客户
     *
     * @param identities 批量删除的参数
     */
    void deleteBatchCustomer(String[] identities);

    /**
     * 用于查询用户数据并返回集合类型数据
     *
     * @param customer
     * @return List类型数据
     */
    List<Customer> queryAllCustomerForList(Customer customer);
}
