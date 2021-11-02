package com.ict.system.service;

import com.ict.system.domain.Role;
import com.ict.system.util.DataGridView;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/0:52
 */
public interface RoleService {


    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * 用于查询，包含模糊查询
     */
    List<Role> queryAllRole(Role role);

    /**
     * 用于分页
     */
    DataGridView queryAllRoleByPage(Role role);

    /**
     * 批量删除
     */
    void deleteBatchRole(Integer[] ids);


    /**
     * 加载角色管理中分配菜单的json
     */
    public DataGridView initRoleMenuTreeJson(Integer roleid);


    /**
     * 保存角色核菜单的关系
     */
    void saveRoleMenu(Role role);
}
