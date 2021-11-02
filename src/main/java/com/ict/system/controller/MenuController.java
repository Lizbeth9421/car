package com.ict.system.controller;

import com.ict.system.constant.constant;
import com.ict.system.domain.Menu;
import com.ict.system.domain.SysUser;
import com.ict.system.service.MenuService;
import com.ict.system.util.DataGridView;
import com.ict.system.util.TreeNode;
import com.ict.system.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/23:10
 */

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService service;

    @ResponseBody
    @RequestMapping("/loadMenuJson")

    /**
     * @Param:
     * @return: 返回有层级关系的json集合
     */
    public List<TreeNode> loadMenuJson(final Menu menu) {
        final SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute(constant.USER_IN_SESSION);
        List<Menu> list = null;
        //设置可用
        menu.setAvailable(constant.AVAILABLE_TRUE);
        if (user.getType().equals(constant.USER_TYPE_SUPER)) {//超级管理员
            list = this.service.queryAllMenu(menu);
        } else {//非超级管理员
            list = this.service.queryAllMenuByUserId(menu, user.getUserid());
        }
        final List<TreeNode> nodes = new ArrayList<>();
        for (final Menu m : list) {
            nodes.add(new TreeNode(m.getId(), m.getPid(), m.getTitle()
                    , m.getIcon(), m.getHref(),
                    m.getSpread().equals(constant.SPREAD_TRUE) ? true : false, m.getTarget()));
        }
        final Integer topPid = 1;//最高级的父节点的id
        return TreeNode.Builder(nodes, topPid);
    }


    /**
     * 跳转到菜单管理界面
     */
    @RequestMapping("/toMenuManger")
    public String toMenuManger() {
        return "system/menuManger";
    }

    /**
     * 跳转到菜单管理左边界面
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuMangerLeft() {
        return "system/menuLeft";
    }

    /**
     * 跳转到菜单管理右边界面
     */
    @RequestMapping("/toMenuRight")
    public String toMenuMangerRight() {
        return "system/menuRight";
    }


    /**
     * 加载菜单管理中左边页面的树所需的json
     */
    @ResponseBody
    @RequestMapping("/loadMenuLeftTreeJson")
    public DataGridView loadMenuLeftJson(final Menu menu) {
        menu.setAvailable(constant.AVAILABLE_TRUE);
        final List<Menu> list = this.service.queryAllMenu(menu);
        final List<TreeNode> nodes = new ArrayList<>();
        for (final Menu m : list) {
            nodes.add(new TreeNode(m.getId(), m.getPid(), m.getTitle()
                    , m.getIcon(), m.getHref(),
                    m.getSpread().equals(constant.SPREAD_TRUE) ? true : false, m.getTarget()));
        }
        return new DataGridView(nodes);
    }

    @ResponseBody
    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(final Menu menu) {
        return this.service.queryAllMenuBypage(menu);
    }
}
