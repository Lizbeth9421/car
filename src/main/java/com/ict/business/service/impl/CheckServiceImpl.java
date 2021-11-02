package com.ict.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.business.domain.Car;
import com.ict.business.domain.Check;
import com.ict.business.domain.Customer;
import com.ict.business.domain.Rent;
import com.ict.business.mapper.CarMapper;
import com.ict.business.mapper.CheckMapper;
import com.ict.business.mapper.CustomerMapper;
import com.ict.business.mapper.RentMapper;
import com.ict.business.service.CheckService;
import com.ict.system.constant.constant;
import com.ict.system.domain.SysUser;
import com.ict.system.util.DataGridView;
import com.ict.system.util.RandomUtils;
import com.ict.system.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/11/22:06
 */
@Service
@Transactional
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(final String checkid) {
        return this.checkMapper.deleteByPrimaryKey(checkid);
    }

    @Override
    public int insert(final Check record) {
        return this.checkMapper.insert(record);
    }

    @Override
    public int insertSelective(final Check record) {
        return this.checkMapper.insertSelective(record);
    }

    @Override
    public Check selectByPrimaryKey(final String checkid) {
        return this.checkMapper.selectByPrimaryKey(checkid);
    }

    @Override
    public int updateByPrimaryKeySelective(final Check record) {
        return this.checkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Check record) {
        return this.checkMapper.updateByPrimaryKey(record);
    }

    @Override
    public void addCheck(final Check check) {
        //将出租单中的归还状态改为已归还
        final Rent rent = this.rentMapper.selectByPrimaryKey(check.getRentid());
        rent.setRentflag(constant.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        //将对应车辆的出租状态改为为出租
        final Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        car.setIsrenting(constant.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
        this.checkMapper.insertSelective(check);
    }

    /**
     * 根据出租单号加载表单和卡片面板的数据
     *
     * @param rentid
     * @return
     */
    @Override
    public Map<String, Object> initCheckFormData(final String rentid) {
        //查询出租单信息
        final Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        //查询车辆信息
        final Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //查询客户信息
        final Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //组装check的数据
        final Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(constant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        final SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute(constant.USER_IN_SESSION);
        check.setOpername(user.getRealname());
        final Map<String, Object> map = new HashMap<>();
        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);
        return map;
    }

    /**
     * 用于查询所有检查单信息
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllCheck(final Check check) {
        final Page page = PageHelper.startPage(check.getPage(), check.getLimit());
        final List<Check> list = this.checkMapper.queryaAllCheck(check);
        return new DataGridView(page.getTotal(), list);
    }

}
