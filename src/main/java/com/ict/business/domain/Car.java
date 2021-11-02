package com.ict.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/07/17:33
 */
@Data
public class Car {
    private String carnumber;

    private String cartype;

    private String color;

    private Double price;

    private Double rentprice;

    private Double deposit;

    private Integer isrenting;

    private String description;

    private String carimg;

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
    private String[] carnumbers;
}