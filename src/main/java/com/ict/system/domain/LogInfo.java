package com.ict.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/04/21:45
 */
@Data
public class LogInfo {
    private Integer id;

    private String loginname;

    private String loginip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logintime;


    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 批量删除
     */
    private Integer[] ids;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}