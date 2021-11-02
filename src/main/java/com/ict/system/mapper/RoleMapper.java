package com.ict.system.mapper;

import com.ict.system.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/0:52
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色
     */
    List<Role> queryAllRole(Role role);

    /**
     * 根据id删除sys_role_menu表中的数据
     */
    int deleteRoleMenuById(Integer roleid);

    /**
     * 根据rid删除sys_role_user表中的数据
     */
    int deleteRoleUserByrid(Integer roleid);

    /**
     * 根据uid删除sys_role_user表中的数据
     */
    int deleteRoleUserByuid(Integer uid);

    /**
     * 保存角色核菜单之间的关系 sys_role_menu
     */
    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    /**
     * 根据用户id查询用户对应的角色
     */
    List<Role> queryRoleByUid(@Param("available") Integer available, @Param("userid") Integer userid);


    /**
     * 保存角色和用户之间的关系
     */
    void insertUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}