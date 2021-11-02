package com.ict.system.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/07/26/22:46
 */
@Data
public class TreeNode {

    private Integer id;

    @JsonProperty("parentId")
    private Integer pid;

    private String title;

    private String icon;

    private String href;

    private Boolean spread;

    private String target;

    private List<TreeNode> children = new ArrayList<>();


    /**
     * 复选框所需的属性
     */
    private String checkArr = "0";


    /**
     * 角色管理中分配菜单的构造器
     *
     * @Param:
     * @return:
     */
    public TreeNode(Integer id,Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    /**
     * 首页左边导航树的构造器
     *
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     * @param target
     * @Author: Lizbeth9421
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.target = target;
    }


    /**
     * 将简单的集合类型转成又层级关系的集合类型
     *
     * @Param:nodes 需要转换的集合
     * @Param:最高级的父节点id
     * @return:具有层级关系的集合类型
     */
    public static List<TreeNode> Builder(List<TreeNode> nodes, Integer topPid) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes) {
            if (n1.getPid() == topPid) {
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes) {
                if (n2.getPid() == n1.getId()) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }
}
