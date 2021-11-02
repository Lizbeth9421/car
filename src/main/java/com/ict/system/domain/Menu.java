package com.ict.system.domain;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/22:43
 */
@Data
public class Menu {
    private Integer id;

    private Integer pid;

    private String title;

    private String href;

    /**
     * 0不展开1展开
     */
    private Integer spread;

    private String target;

    private String icon;

    /**
     * 0不可用1可用
     */
    private Integer available;


    /**
     * 分页的参数
     */
    private Integer page;
    private Integer limit;
}