package com.ict.business.controller;

import com.ict.business.domain.Car;
import com.ict.business.service.CarService;
import com.ict.system.constant.constant;
import com.ict.system.util.AppFileUtils;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 车辆管理的控制器
 *
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/17:34
 */
@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService service;

    /**
     * 查询所有汽车信息
     * 包含模糊查询和分页
     *
     * @param car
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadAllCar")
    public DataGridView loadAllCar(final Car car) {
        return this.service.queryAllCar(car);
    }

    /**
     * 添加车辆信息
     *
     * @param car
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCar")
    public ResultObj addCar(final Car car) {
        try {
            car.setCreatetime(new Date());
            //判断是否为默认图片，若不是去掉临时文件后缀
            if (!car.getCarimg().equals(constant.DEFAULT_CAR_IMG)) {
                final String fileName = AppFileUtils.updateFileName(car.getCarimg(), constant.FILE_UPLOAD_TEMP);
                car.setCarimg(fileName);
            }
            this.service.insertSelective(car);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改车辆信息
     *
     * @param car
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCar")
    public ResultObj updateCar(final Car car) {
        try {
            final String carimg = car.getCarimg();
            if (carimg.endsWith(constant.FILE_UPLOAD_TEMP)) {
                final String filePath = AppFileUtils.updateFileName(carimg, constant.FILE_UPLOAD_TEMP);
                car.setCarimg(filePath);
            }
            //删除修改之前上传的图片
            final Car car1 = this.service.selectByPrimaryKey(car.getCarnumber());
            if (!car1.getCarimg().equals(constant.DEFAULT_CAR_IMG)) {
                AppFileUtils.deleteFileUsePath(car1.getCarimg());
            }
            this.service.updateByPrimaryKeySelective(car);
            return ResultObj.UPDATE_SUCCESS;
        } catch (final Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆信息
     *
     * @param carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCar")
    public ResultObj deleteCar(final String carnumber) {
        try {
            this.service.deleteByPrimaryKey(carnumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除车辆信息
     *
     * @param carnumbers
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteBatchCar")
    public ResultObj deleteBatchCar(final String[] carnumbers) {
        try {
            this.service.deleteBatchCar(carnumbers);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }
}
