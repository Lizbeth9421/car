package com.ict.system.util;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/28/1:07
 * 该类用于加载下拉树的json
 */
@Data
public class DataGridView {
    private Integer code = 0;
    private String msg = "";
    private Long count;
    private Object data;


    public DataGridView() {
    }


    /**
     * 用于分页的构造器
     */
    public DataGridView(final Long count, final Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView(final Object data) {
        this.data = data;
    }
}
