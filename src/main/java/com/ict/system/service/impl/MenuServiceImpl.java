package com.ict.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ict.system.domain.Menu;
import com.ict.system.mapper.MenuMapper;
import com.ict.system.service.MenuService;
import com.ict.system.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/22:44
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(final Integer id) {
        return this.menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(final Menu record) {
        return this.menuMapper.insert(record);
    }

    @Override
    public int insertSelective(final Menu record) {
        return this.menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(final Integer id) {
        return this.menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(final Menu record) {
        return this.menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Menu record) {
        return this.menuMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询所有菜单
     */
    @Override
    public List<Menu> queryAllMenu(final Menu menu) {
        return this.menuMapper.queryAllMenu(menu);
    }

    /**
     * 根据用户id查询菜单
     */
    @Override
    public List<Menu> queryAllMenuByUserId(final Menu menu, final Integer id) {
        return this.menuMapper.queryAllMenuByUserId(menu.getAvailable(), id);
    }

    @Override
    public DataGridView queryAllMenuBypage(final Menu menu) {
        final Page<Menu> page = PageHelper.startPage(menu.getPage(), menu.getLimit());
        final List<Menu> list = this.menuMapper.queryAllMenu(menu);
        return new DataGridView(page.getTotal(), list);
    }

}
