package com.ict.system.constant;

/**
 * @Author: Lizbeth9421
 */
public enum constant {
    ;
    /**
     * 用户信息在sesiion中的名字
     */
    public static final String USER_IN_SESSION = "USER_IN_SESSION";

    /**
     * 验证码在session中的名字
     */
    public static final String CODE_IN_SESSION = "CODE_IN_SESSION";
    /**
     * 是否为超级管理员
     */
    public static final Integer USER_TYPE_SUPER = 1;
    public static final Integer USER_TYPE_NORMAL = 2;

    /**
     * 可用状态
     */
    public static final Integer AVAILABLE_TRUE = 1;
    public static final Integer AVAILABLE_FALSE = 0;

    /**
     * 菜单栏是否展开
     */
    public static final Integer SPREAD_TRUE = 1;
    public static final Integer SPREAD_FALSE = 0;

    public static final String ADD_SUCCESS = "添加成功";
    public static final String ADD_ERROR = "添加失败";

    public static final String UPDATE_SUCCESS = "更新成功";
    public static final String UPDATE_ERROR = "更新失败";

    public static final String DELETE_SUCCESS = "删除成功";
    public static final String DELETE_ERROR = "删除失败";

    public static final String RESET_SUCCESS = "重置成功";
    public static final String RESET_ERROR = "重置失败";

    public static final String DISPATCH_SUCCESS = "分配成功";
    public static final String DISPATCH_ERROR = "分配失败";


    /**
     * 操作成功核操作失败
     */
    public static final Integer CODE_SUCCESS = 0;
    public static final Integer CODE_ERROR = -1;


    /**
     * 复选框是否选中
     */
    public static final String CHECKARR_TRUE = "1";
    public static final String CHECKARR_FALSE = "0";


    /**
     * 用户的初始密码
     */
    public static final String INITIAL_PASSWORD = "123456";


    /**
     * 临时文件的文件后缀
     */
    public static final String FILE_UPLOAD_TEMP = "_temp";


    /**
     * 上传车辆图片时的的默认图片地址
     */
    public static final String DEFAULT_CAR_IMG = "images/defaultcarimage.jpg";

    /**
     * 单号的前缀
     */
    public static final String CAR_ORDER_CZ = "CZ";
    public static final String CAR_ORDER_JC = "JC";

    /**
     * 归还状态
     */
    public static final Integer RENT_BACK_TRUE = 1;
    public static final Integer RENT_BACK_FALSE = 0;

    /**
     * 出租状态
     */
    public static final Integer RENT_CAR_TRUE = 1;
    public static final Integer RENT_CAR_FALSE = 0;

    public static final String RENT_SUCCESS = "出租成功";
    public static final String RENT_ERROR = "出租失败";

    /**
     * 客户管理中的数据导出生成的文件名
     */
    public static final String CUSTOMER_EXPORT_NAME = "客户数据";

    /**
     * 公用常量
     */
    public static final Integer CODE_ZERO = 0;
    public static final Integer CODE_ONE = 1;
    public static final Integer CODE_TWO = 2;
    public static final Integer CODE_THREE = 3;


}
