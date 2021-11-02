package com.ict.system.controller;

import com.ict.system.domain.Role;
import com.ict.system.service.RoleService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/1:38
 */

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService service;


    /**
     * 跳转到roleManger.jsp页面
     */

    @RequestMapping("/toRoleManger")
    public String toRoleManger() {
        return "system/roleManger";
    }


    /**
     * 加载角色管理中的表格数据
     */
    @ResponseBody
    @RequestMapping("/loadRoleJson")
    public DataGridView loadRoleJson(Role role) {
        return service.queryAllRoleByPage(role);
    }


    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("/addRole")
    public ResultObj addRole(Role role) {
        try {
            service.insertSelective(role);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 批量删除角色
     */
    @ResponseBody
    @RequestMapping("/deleteBatchRole")
    public ResultObj deleteBatchRole1(Role role) {
        try {
            service.deleteBatchRole(role.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping("/deleteRole")
    public ResultObj deleteRole(Role role) {
        try {
            service.deleteByPrimaryKey(role.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /*
     *更新角色
     */
    @ResponseBody
    @RequestMapping("/updateRole")
    public ResultObj updateRole(Role role) {
        try {
            service.updateByPrimaryKeySelective(role);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 加载角色管理中分配菜单中的树json
    */
    @ResponseBody
    @RequestMapping("/initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){
        return service.initRoleMenuTreeJson(roleid);
    }


    /**
     * 保持角色核菜单的关系
    */
    @ResponseBody
    @RequestMapping("/saveRoleMenu")
    public ResultObj saveRoleMenu(Role role){
        try {
            service.saveRoleMenu(role);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
