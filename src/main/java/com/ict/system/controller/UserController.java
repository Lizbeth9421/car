package com.ict.system.controller;

import com.ict.system.domain.SysUser;
import com.ict.system.service.SysUserService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/31/0:15
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService service;


    /**
     * 跳转到userManger.jsp页面
     */
    @RequestMapping("/toUserManger")
    public String toUserManger() {
        return "/system/userManger";
    }


    /**
     * 加载用户管理中的json数据，包含模糊查询
     */
    @ResponseBody
    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(final SysUser user) {
        return this.service.queryAllUser(user);
    }


    /**
     * 重置用户密码
     */
    @ResponseBody
    @RequestMapping("/resetUserPassword")
    public ResultObj resetUserPassword(final Integer userid) {
        try {
            this.service.resetUserPwd(userid);
            return ResultObj.RESET_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 添加用户
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public ResultObj addUser(final SysUser user) {
        try {
            this.service.addUser(user);
            return ResultObj.ADD_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public ResultObj updateUser(final SysUser user) {
        try {
            this.service.updateByPrimaryKeySelective(user);
            return ResultObj.UPDATE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public ResultObj deleteUser(final Integer userid) {
        try {
            this.service.deleteUser(userid);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @ResponseBody
    @RequestMapping("/deleteBatchUser")
    public ResultObj deleteBatchUser(final Integer[] ids) {
        try {
            this.service.deleteBatchUser(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (final Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 加载分配角色中的表格信息
     */
    @ResponseBody
    @RequestMapping("/initUserRole")
    public DataGridView initUserRole(final Integer userid) {
        return this.service.initUserRole(userid);

    }

    @ResponseBody
    @RequestMapping("/saveUserRole")
    /**
     * 保存用户和角色的关系
     */
    public ResultObj saveUserRole(final SysUser user) {
        try {
            this.service.saveUserRole(user);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (final Exception e) {
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
