package com.ict.system.service;

import com.ict.system.domain.SysUser;
import com.ict.system.util.DataGridView;

/**
 * @Author: Lizbeth9421
 */
public interface SysUserService {


    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    /**
     * 用于登录
     */
    SysUser checkLogin(SysUser user);

    /**
     * 查询用户，包含模糊查询
     */
    DataGridView queryAllUser(SysUser user);

    /**
     * 添加用户
     */
    public void addUser(SysUser user);

    /**
     * 根据id删除用户
     *
     * @param userid
     */
    public void deleteUser(Integer userid);

    /**
     * 批量删除用户
     */
    public void deleteBatchUser(Integer[] ids);

    /**
     * 重置密码
     *
     * @param userid
     */
    public void resetUserPwd(Integer userid);

    DataGridView initUserRole(Integer userid);

    void saveUserRole(SysUser user);
}
