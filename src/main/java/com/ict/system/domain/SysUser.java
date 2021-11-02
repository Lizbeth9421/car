package com.ict.system.domain;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 */
@Data
public class SysUser {
    private Integer userid;

    private String loginname;

    private String identity;

    private String realname;

    /**
     * 0女1男
     */
    private Integer sex;

    private String address;

    private String phone;

    private String password;

    private String position;

    /**
     * 1，超级管理员,2，系统用户
     */
    private Integer type;

    private Integer available;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;


    /**
     * 批量删除的参数
     */
    private Integer[] ids;
}