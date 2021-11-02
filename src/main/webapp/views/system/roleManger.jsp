<%--
  Created by IntelliJ IDEA.
  User: Lizbeth921
  Date: 2021/7/29
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrom=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="/favicon.ico">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <link rel="stylesheet" href="/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="/layui_ext/dtree/font/dtreefont.css">
    <script src="/layui/layui.js"></script>
</head>
<body>
<!--搜索条件开始-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="rolename" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">角色备注:</label>
            <div class="layui-input-inline">
                <input type="text" name="roledesc" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否可用:</label>
            <div class="layui-input-inline">
                <input type="radio" name="available" value="1" title="可用">
                <input type="radio" name="available" value="0" title="不可">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">清空</button>
        </div>
    </div>
</form>
<!--搜索条件结束-->
<!--数据表格开始-->
<table id="roleTable" lay-filter="roleTable"></table>
<div style="display: none;" id="roleToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div id="roleBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="selectRoleMenu">分配菜单</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!--数据表格结束-->
<!--添加和修改的弹出层开始-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称:</label>
            <div class="layui-input-block">
                <input type="hidden" name="roleid">
                <input type="text" name="rolename" placeholder="请输入角色名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色备注:</label>
            <div class="layui-input-block">
                <input type="text" name="roledesc" placeholder="请输入角色备注" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否可用:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="available" value="1" checked="checked" title="可用">
                    <input type="radio" name="available" value="0" title="不可用">
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!--添加和修改的弹出层结束-->
<!--角色分配弹出成开始-->
<div style="display: none" id="selectRoleMenu">
    <ul class="dtree" id="menuTree" data-id="0"></ul>
</div>
<!--角色分配弹出成结束-->
<script>
    layui.extend({
        dtree: '/layui_ext/dist/dtree'
    }).use(['jquery', 'layer', 'form', 'table', 'dtree'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree
        //渲染数据表格
        var tableIns = table.render({
            elem: '#roleTable',
            title: '用户数据表',
            url: '/role/loadRoleJson',
            cellMinWidth: 100,
            height: 'full-150',
            toolbar: '#roleToolBar',
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'roleid', title: 'ID', align: 'center'},
                {field: 'rolename', title: '角色名称', align: 'center'},
                {field: 'roledesc', title: '角色备注', align: 'center'},
                {
                    field: 'available', title: '是否可用', align: 'center', templet: function (d) {
                        return d.available == '1' ? '<font color=#9acd32>可用</font>' : '<font color=red>不可用</font>';
                    }
                },
                {fixed: 'right', title: '操作', toolbar: '#roleBar', width: 220, align: 'center'}
            ]],
            done: function (data, curr, count) {
                //不是第一页时如果当前返回的的数据为0那么就返回上一页
                if (data.data.length == 0 && count != 1 && couunt != 0) {
                    tableIns.reload({
                        page: {curr: curr = 1}
                    });
                }
            }
        });
        var url, mainIndex;
        //模糊查询
        $("#doSearch").on('click', function () {
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "/role/loadRoleJson?" + params,
                page: {curr: 1}
            });
        })

        //监听头部工具条事件
        table.on("toolbar(roleTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddRole();
                    break;
                case 'deleteBatch' :
                    deleteBatch();
                    break;
            }
        });

        //监控行工具条事件
        table.on('tool(roleTable)', function (obj) {
            var data = obj.data;//获取当前行的数据
            var layEvent = obj.event;//获取lay-event对应的值
            if (layEvent == 'del') {
                layer.confirm("你真的要删除<font color='red'>" + data.rolename + "</font>" + "这个角色吗？", function (index) {
                    //向服务段发送删除指令
                    $.post("/role/deleteRole", {roleid: data.roleid}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent == "edit") {
                openUpdateRole(data);
            } else if (layEvent == 'selectRoleMenu') {
                openselectRoleMenu(data);
            }
        })

        //打开分配菜单的弹出层
        function openselectRoleMenu(data) {
            var menuTree;
            mainIndex = layer.open({
                type: 1,
                title: '分配【' + data.rolename + '】的角色',
                content: $("#selectRoleMenu"),
                area: ['400px', '500px'],
                btnAlign: 'c',
                btn: ['<div class="layui-icon layui-icon-release">确认分配</div>', '<div class="layui-icon layui-icon-close">取消分配</div>'],
                yes: function (index, layero) {
                    var nodes = dtree.getCheckbarNodesParam("menuTree");
                    var roleid = data.roleid;
                    var params = "roleid=" + roleid;
                    $.each(nodes, function (i, item) {
                        params += "&ids=" + item.nodeId;
                    });
                    //保持角色核菜单之间的关系
                    $.post("/role/saveRoleMenu", params, function (obj) {
                        layer.msg(obj.msg);
                    });
                    layer.close(mainIndex);
                },
                success: function (index) {
                    //初始化树
                    menuTree = dtree.render({
                        elem: "#menuTree",
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        response: {message: "msg", statusCode: 0},  //修改response中返回数据的定义
                        dataFormat: "list",  //配置data的风格为list
                        checkbar: true,
                        checkbarType: "all", // 默认就是all，其他的值为： no-all  p-casc   self  only\
                        checkbarData: "choose",
                        url: "/role/initRoleMenuTreeJson?roleid=" + data.roleid // 使用url加载（可与data加载同时存在）
                    });
                }
            });
        }

        //打开添加页面
        function openAddRole() {
            mainIndex = layer.open({
                type: 1,
                title: '添加角色',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '300px'],
                success: function (index) {
                    //由于添加与修改用的同一个弹出层，需要清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/role/addRole";
                }
            });
        }

        //打开更新页面
        function openUpdateRole(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改用户",
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '300px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "/role/updateRole";
                }
            });
        }

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('roleTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (index, item) {
                if (index == 0) {
                    params = params + "ids=" + item.roleid;
                } else {
                    params = params + "&ids=" + item.roleid;
                }
            });
            layer.confirm('你真的要删除这些角色么？', function (index) {
                //向服务端发送删除指令
                $.post("/role/deleteBatchRole", params, function (result) {
                    layer.msg(result.msg);
                    //刷新表格
                    tableIns.reload();
                });

            });
        }

        //保存
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            // $.post(url, params, function (obj) {
            //     layer.msg(obj.msg);
            //     //关闭弹出层
            //     layer.close(mainIndex);
            //     //刷新数据表格
            //     tableIns.reload();
            // });
            $.ajax({
                type: "POST",
                url: url,
                data: params,
                async: true,
                success: function (obj) {
                    layer.msg(obj.msg);
                    //关闭弹出层
                    layer.close(mainIndex);
                    //刷新数据表格
                    tableIns.reload();
                }
            });
        });
    });
</script>
</body>
</html>
