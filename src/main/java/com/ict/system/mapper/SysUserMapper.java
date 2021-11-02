package com.ict.system.mapper;

import com.ict.system.domain.SysUser;

import java.util.List;

/**
 * @Author: Lizbeth9421
 */
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser login(SysUser user);


    /**
     * 查询用户，包含模糊查询
     */
    List<SysUser> queryAllUser(SysUser user);

}