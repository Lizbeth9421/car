package com.ict.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.business.domain.Customer;
import com.ict.business.mapper.CustomerMapper;
import com.ict.business.service.CustomerService;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/22:35
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(final String identity) {
        return this.customerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public int insert(final Customer record) {
        return this.customerMapper.insert(record);
    }

    @Override
    public int insertSelective(final Customer record) {
        return this.customerMapper.insertSelective(record);
    }

    @Override
    public Customer selectByPrimaryKey(final String identity) {
        return this.customerMapper.selectByPrimaryKey(identity);
    }

    @Override
    public int updateByPrimaryKeySelective(final Customer record) {
        return this.customerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Customer record) {
        return this.customerMapper.updateByPrimaryKey(record);
    }

    /**
     * 用于查询所有客户
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllCustomer(final Customer customer) {
        final Page<Object> page = PageHelper.startPage(customer.getPage(), customer.getLimit());
        final List<Customer> list = this.customerMapper.queryAllCustomer(customer);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据身份证号批量删除客户
     *
     * @param identities 批量删除的参数
     */
    @Override
    public void deleteBatchCustomer(final String[] identities) {
        for (final String identity : identities) {
            this.customerMapper.deleteByPrimaryKey(identity);
        }
    }

    /**
     * 用于查询用户数据并返回集合类型数据
     *
     * @param customer
     * @return List类型数据
     */
    @Override
    public List<Customer> queryAllCustomerForList(final Customer customer) {
        return this.customerMapper.queryAllCustomer(customer);
    }

}
