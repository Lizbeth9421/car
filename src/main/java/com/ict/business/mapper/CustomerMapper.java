package com.ict.business.mapper;

import com.ict.business.domain.Customer;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/22:35
 */
public interface CustomerMapper {
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
    List<Customer> queryAllCustomer(Customer customer);
}