package com.ict.system.domain;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/0:52
 */
@Data
public class Role {
    private Integer roleid;

    private String rolename;

    private String roledesc;

    private Integer available;


    /**
     * 分页参数
    */
    private Integer page;
    private Integer limit;


    /**
     * 批量删除的参数
    */
    private Integer [] ids;
}