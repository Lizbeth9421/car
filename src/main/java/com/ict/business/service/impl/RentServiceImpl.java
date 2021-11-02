package com.ict.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.business.domain.Car;
import com.ict.business.domain.Rent;
import com.ict.business.mapper.CarMapper;
import com.ict.business.mapper.RentMapper;
import com.ict.business.service.RentService;
import com.ict.system.constant.constant;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/09/16:56
 */
@Service
@Transactional
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public int deleteByPrimaryKey(final String rentid) {
        final Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        final Car car = new Car();
        //设置该出租单号对应车辆的车牌号
        car.setCarnumber(rent.getCarnumber());
        //设置该车辆的出租状态为为出租
        car.setIsrenting(constant.RENT_BACK_FALSE);
        //更新该车辆的信息
        this.carMapper.updateByPrimaryKeySelective(car);
        return this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public int insert(final Rent record) {
        return this.rentMapper.insert(record);
    }

    @Override
    public int insertSelective(final Rent record) {
        final Car car = new Car();
        //更改bus_car中的出租状态
        car.setCarnumber(record.getCarnumber());
        car.setIsrenting(constant.RENT_CAR_TRUE);
        this.carMapper.updateByPrimaryKeySelective(car);
        return this.rentMapper.insertSelective(record);
    }

    @Override
    public Rent selectByPrimaryKey(final String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }

    @Override
    public int updateByPrimaryKeySelective(final Rent record) {
        return this.rentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Rent record) {
        return this.rentMapper.updateByPrimaryKey(record);
    }

    /**
     * 用于查询所有出租单信息
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllRent(final Rent rent) {
        final Page<Object> page = PageHelper.startPage(rent.getPage(), rent.getLimit());
        final List<Rent> list = this.rentMapper.queryAllRent(rent);
        return new DataGridView(list);
    }

}
