package com.ict.business.service;

import com.ict.business.domain.Car;
import com.ict.system.util.DataGridView;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/17:33
 */
public interface CarService {


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
    DataGridView queryAllCar(Car car);

    /**
     * 根据车牌号批量删除车辆
     *
     * @param carnumbers 车牌号
     */
    void deleteBatchCar(String[] carnumbers);
}
