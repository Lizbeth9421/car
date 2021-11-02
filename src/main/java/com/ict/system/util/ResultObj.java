package com.ict.system.util;

import com.ict.system.constant.constant;
import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/15:54
 */
@Data
public class ResultObj {
    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR = new ResultObj(constant.CODE_ERROR, constant.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR = new ResultObj(constant.CODE_ERROR, constant.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR = new ResultObj(constant.CODE_ERROR, constant.DELETE_ERROR);
    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR = new ResultObj(constant.CODE_ERROR, constant.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR = new ResultObj(constant.CODE_ERROR, constant.DISPATCH_ERROR);
    /**
     * 出租成功
     */
    public static final ResultObj RENT_SUCCESS = new ResultObj(constant.CODE_SUCCESS, constant.RENT_SUCCESS);
    /**
     * 出租成功
     */
    public static final ResultObj RENT_ERROR = new ResultObj(constant.CODE_SUCCESS, constant.RENT_ERROR);
    /**
     * 状态码0
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(constant.CODE_SUCCESS);
    /**
     * 状态码-1
     */
    public static final ResultObj STATUS_FALSE = new ResultObj(constant.CODE_ERROR);
    private Integer code = 0;
    private String msg;


    private ResultObj(final Integer code, final String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private ResultObj(final Integer code) {
        super();
        this.code = code;
    }


}
