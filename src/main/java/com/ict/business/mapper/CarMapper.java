package com.ict.business.mapper;

import com.ict.business.domain.Car;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/17:33
 */
public interface CarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carnumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    /**
     * 用于查询所有车辆
     * 包含分页和模糊查询
     */
    List<Car> queryAllCar(Car car);
}