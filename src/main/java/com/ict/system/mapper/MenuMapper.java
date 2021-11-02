package com.ict.system.mapper;

import com.ict.system.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/22:44
 */
public interface MenuMapper {
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
     * 根据角色id查询菜单
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer available, @Param("rid") Integer rid);

    /**
     * 根据用户id查询菜单
     */
    List<Menu> queryAllMenuByUserId(@Param("available") Integer available, @Param("uid") Integer uid);
}