package com.ict.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.system.constant.constant;
import com.ict.system.domain.Menu;
import com.ict.system.domain.Role;
import com.ict.system.mapper.MenuMapper;
import com.ict.system.mapper.RoleMapper;
import com.ict.system.service.RoleService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/29/0:52
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int insert(final Role record) {
        return this.roleMapper.insert(record);
    }

    @Override
    public int insertSelective(final Role record) {
        return this.roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(final Integer roleid) {
        return this.roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int updateByPrimaryKeySelective(final Role record) {
        return this.roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Role record) {
        return this.roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> queryAllRole(final Role role) {
        return this.roleMapper.queryAllRole(role);
    }

    @Override
    public int deleteByPrimaryKey(final Integer roleid) {
        this.roleMapper.deleteRoleMenuById(roleid);
        this.roleMapper.deleteRoleUserByrid(roleid);
        return this.roleMapper.deleteByPrimaryKey(roleid);
    }

    @Override
    public DataGridView queryAllRoleByPage(final Role role) {
        final Page<Object> page = PageHelper.startPage(role.getPage(), role.getLimit());
        final List<Role> data = this.roleMapper.queryAllRole(role);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void deleteBatchRole(final Integer[] ids) {
        for (final Integer roleid : ids) {
            this.deleteByPrimaryKey(roleid);
        }

    }

    @Override
    public DataGridView initRoleMenuTreeJson(final Integer roleid) {
        // 查询所有的可用的菜单
        final Menu menu = new Menu();
        menu.setAvailable(constant.AVAILABLE_TRUE);
        final List<Menu> allMenu = this.menuMapper.queryAllMenu(menu);
        // 根据角色ID查询当前角色拥有的菜单
        final List<Menu> roleMenu = this.menuMapper.queryMenuByRoleId(constant.AVAILABLE_TRUE, roleid);
        final List<TreeNode> data = new ArrayList<>();
        for (final Menu m1 : allMenu) {
            String checkArr = constant.CHECKARR_FALSE;//默认为不选中
            for (final Menu m2 : roleMenu) {
                if (m2.getId().equals(m1.getId())) {
                    checkArr = constant.CHECKARR_TRUE;
                    break;
                }
            }
            data.add(new TreeNode(m1.getId(), m1.getPid(), m1.getTitle(), m1.getSpread().equals(constant.SPREAD_TRUE) ? true : false, checkArr));
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(final Role role) {
        //删除原先sys_role_menu的roleid对应的数据
        this.roleMapper.deleteRoleMenuById(role.getRoleid());
        //添加新的菜单关系
        final Integer[] mids = role.getIds();
        for (final Integer mid : mids) {
            this.roleMapper.insertRoleMenu(role.getRoleid(), mid);
        }

    }

}
