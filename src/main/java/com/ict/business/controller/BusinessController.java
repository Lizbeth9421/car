package com.ict.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/23:24
 * 用于控制业务逻辑的页面跳转
 */
@Controller
@RequestMapping("/business")
public class BusinessController {
    /**
     * 跳转到customerManger页面
     *
     * @return
     */
    @RequestMapping("/toCustomerManger")
    public String toCustomerManger() {
        return "/business/customerManger";
    }

    /**
     * 跳转到carManger页面
     *
     * @return
     */
    @RequestMapping("/toCarManger")
    public String toCarManger() {
        return "/business/carManger";
    }

    /**
     * 跳转到汽车出租页面
     *
     * @return
     */
    @RequestMapping("/toRentCarManger")
    public String toRentCarManger() {
        return "/business/rentCarManger";
    }

    /**
     * 跳转到汽车出租页面
     *
     * @return
     */
    @RequestMapping("/toRentManger")
    public String toRentManger() {
        return "/business/rentManger";
    }

    /**
     * 跳转到汽车出租页面
     *
     * @return
     */
    @RequestMapping("/toCheckCarManger")
    public String toCheckCarManger() {
        return "/business/checkCarManger";
    }

    /**
     * 跳转到检查单管理页面
     *
     * @return
     */
    @RequestMapping("/toCheckManger")
    public String toCheckManger() {
        return "/business/checkManger";
    }
}
