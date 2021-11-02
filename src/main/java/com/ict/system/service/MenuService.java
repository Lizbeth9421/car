package com.ict.system.service;

import com.ict.system.domain.Menu;
import com.ict.system.util.DataGridView;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/22:44
 */
public interface MenuService {


    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);


    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     * 根据用户id查询菜单
     */
    List<Menu> queryAllMenuByUserId(Menu menu, Integer id);


    /**
     * 用于分页的查询
     */
    DataGridView queryAllMenuBypage(Menu menu);
}
