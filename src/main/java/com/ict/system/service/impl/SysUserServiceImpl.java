package com.ict.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.system.constant.constant;
import com.ict.system.domain.Role;
import com.ict.system.domain.SysUser;
import com.ict.system.mapper.RoleMapper;
import com.ict.system.mapper.SysUserMapper;
import com.ict.system.service.SysUserService;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lizbeth9421
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(final Integer userid) {
        return this.sysUserMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(final SysUser record) {
        return this.sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(final SysUser record) {
        return this.sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(final Integer userid) {
        return this.sysUserMapper.selectByPrimaryKey(userid);
    }

    @Override
    public int updateByPrimaryKeySelective(final SysUser record) {
        return this.sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final SysUser record) {
        return this.sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public SysUser checkLogin(final SysUser user) {
        //生成明文
        final String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        return this.sysUserMapper.login(user);
    }

    /**
     * 用于查询所有用户
     * 包含分页和模糊查询
     */
    @Override
    public DataGridView queryAllUser(final SysUser user) {
        final Page<Object> page = PageHelper.startPage(user.getPage(), user.getLimit());
        final List<SysUser> data = this.sysUserMapper.queryAllUser(user);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addUser(final SysUser user) {
        //设置用户初始密码123456
        user.setPassword(DigestUtils.md5DigestAsHex(constant.INITIAL_PASSWORD.getBytes()));
        //设置用户的类型，默认为普通用户
        user.setType(constant.USER_TYPE_NORMAL);
        //设置用户为可用
        user.setAvailable(constant.AVAILABLE_TRUE);
        this.sysUserMapper.insertSelective(user);
    }

    @Override
    public void deleteUser(final Integer userid) {
        //删除sys_user中的数据
        this.sysUserMapper.deleteByPrimaryKey(userid);
        //删除sys_role_user中的数据
        this.roleMapper.deleteRoleUserByuid(userid);
    }

    /**
     * 批量删除
     *
     * @param ids 批量删除的参数
     */
    @Override
    public void deleteBatchUser(final Integer[] ids) {
        for (final Integer userid : ids) {
            this.deleteUser(userid);
        }
    }


    /**
     * 重置密码
     */
    @Override
    public void resetUserPwd(final Integer userid) {
        final SysUser user = new SysUser();
        user.setUserid(userid);
        user.setPassword(DigestUtils.md5DigestAsHex(constant.INITIAL_PASSWORD.getBytes()));
        this.sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 加载分配角色中的表格信息
     */
    @Override
    public DataGridView initUserRole(final Integer userid) {
        //查询出所有可用的角色
        final Role role = new Role();
        role.setAvailable(constant.AVAILABLE_TRUE);
        final List<Role> allRole = this.roleMapper.queryAllRole(role);
        //根据用户id查询已拥有的角色
        final List<Role> list = this.roleMapper.queryRoleByUid(constant.AVAILABLE_TRUE, userid);
        final List<Map<String, Object>> data = new ArrayList<>();
        for (final Role r1 : allRole) {
            Boolean LAY_CHECKED = false;//判断是否选中
            for (final Role r2 : list) {
                if (r2.getRoleid().equals(r1.getRoleid())) {
                    LAY_CHECKED = true;
                }
            }
            final Map<String, Object> map = new HashMap<>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    /**
     * 保存用户和角色的关系
     */
    @Override
    public void saveUserRole(final SysUser user) {
        //根据用户id删除sys_role-user中的数据
        this.roleMapper.deleteRoleUserByuid(user.getUserid());
        //保存用户与角色之间的关系
        final Integer[] roleIds = user.getIds();
        if (roleIds != null && roleIds.length > 0) {
            for (final Integer rid : roleIds) {
                this.roleMapper.insertUserRole(user.getUserid(), rid);
            }
        }
    }

}
