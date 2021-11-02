package com.ict.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/06/22:35
 */
@Data
public class Customer {
    /**
     * 身份证
     */
    private String identity;

    /**
     * 姓名
     */
    private String custname;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 职位
     */
    private String career;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;


    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 批量删除
     */
    private String[] identities;
}