package com.ict.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.business.domain.Car;
import com.ict.business.mapper.CarMapper;
import com.ict.business.service.CarService;
import com.ict.system.constant.constant;
import com.ict.system.util.AppFileUtils;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/17:33
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public int deleteByPrimaryKey(final String carnumber) {
        //查询出该车辆信息的图片路径
        final String carimg = this.carMapper.selectByPrimaryKey(carnumber).getCarimg();
        //删除图片
        if (!carimg.equals(constant.DEFAULT_CAR_IMG)) {
            AppFileUtils.deleteFileUsePath(carimg);
        }
        return this.carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public int insert(final Car record) {
        return this.carMapper.insert(record);
    }

    @Override
    public int insertSelective(final Car record) {
        return this.carMapper.insertSelective(record);
    }

    @Override
    public Car selectByPrimaryKey(final String carnumber) {
        return this.carMapper.selectByPrimaryKey(carnumber);
    }

    @Override
    public int updateByPrimaryKeySelective(final Car record) {
        return this.carMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Car record) {
        return this.carMapper.updateByPrimaryKey(record);
    }

    /**
     * 用于查询所有车辆
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllCar(final Car car) {
        final Page<Object> page = PageHelper.startPage(car.getPage(), car.getLimit());
        final List<Car> list = this.carMapper.queryAllCar(car);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据车牌号批量删除车辆
     *
     * @param carnumbers 车牌号
     */
    @Override
    public void deleteBatchCar(final String[] carnumbers) {
        for (final String carnumber : carnumbers) {
            this.deleteByPrimaryKey(carnumber);
        }
    }

}
