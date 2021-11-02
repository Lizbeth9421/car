<%--
  Created by IntelliJ IDEA.
  User: Lizbeth921
  Date: 2021/7/31
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
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
<body>
<!--搜索条件开始-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户姓名:</label>
            <div class="layui-input-inline">
                <input type="text" name="realname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">登陆名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="loginname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户地址:</label>
            <div class="layui-input-inline">
                <input type="text" name="address" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户电话:</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否可用:</label>
            <div class="layui-input-inline">
                <input type="radio" name="available" value="1" title="可用">
                <input type="radio" name="available" value="0" title="不可用">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!--搜索条件结束-->
<!--数据表格开始-->
<table id="userTable" lay-filter="userTable"></table>
<div style="display: none;" id="userToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div id="roleBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="resetUserPassword">重置密码</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="selectUserRole">分配角色</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!--数据表格结束-->
<!--添加和修改的弹出层开始-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户姓名:</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="userid">
                    <input type="text" name="realname" lay-verify="required" placeholder="请输入用户姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">登陆名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginname" lay-verify="required" placeholder="请输入用户登陆名称" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity" placeholder="请输入用户身份证号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" placeholder="请输入用户地址" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" placeholder="请输入用户电话" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户职位:</label>
                <div class="layui-input-inline">
                    <input type="text" name="position" placeholder="请输入用户职位" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" checked="checked" title="男">
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>
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
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">清空
                </button>
            </div>
        </div>
    </form>

</div>
<!--添加和修改的弹出层结束-->
<!--角色分配弹出成开始-->
<div style="display: none" id="selectUserRole">
    <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
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
            elem: '#userTable',
            title: '用户数据表',
            url: '/user/loadAllUser',
            cellMinWidth: 100,
            height: 'full-250',
            toolbar: '#userToolBar',
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'userid', title: 'ID', align: 'center'}
                , {field: 'realname', title: '用户姓名', align: 'center', width: '150'}
                , {field: 'loginname', title: '登陆名', align: 'center'}
                , {field: 'identity', title: '身份证号', align: 'center', width: '220'}
                , {field: 'phone', title: '用户电话', align: 'center', width: '250'}
                , {field: 'address', title: '用户地址', align: 'center', width: '300'}
                , {
                    field: 'sex', title: '性别', align: 'center', templet: function (d) {
                        return d.sex == '1' ? '<font class="layui-icon-male layui-icon" color=#7fffd4>男</font>' : '<font class="layui-icon layui-icon-female" color=red>女</font>';
                    }
                },
                {
                    field: 'available', title: '是否可用', align: 'center', templet: function (d) {
                        return d.available == '1' ? '<font color=#9acd32>可用</font>' : '<font color=red>不可用</font>';
                    }
                },
                {fixed: 'right', title: '操作', toolbar: '#roleBar', width: '320px', align: 'center'}
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
                url: "/user/loadAllUser?" + params,
                page: {curr: 1}
            });
        })

        //监听头部工具条事件
        table.on("toolbar(userTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddUser();
                    break;
                case 'deleteBatch' :
                    deleteBatch();
                    break;
            }
        });

        //监控行工具条事件
        table.on('tool(userTable)', function (obj) {
            var data = obj.data;//获取当前行的数据
            var layEvent = obj.event;//获取lay-event对应的值
            if (layEvent == 'del') {
                layer.confirm("你真的要删除<font color='red'>" + data.realname + "</font>" + "这个角色吗？", function (index) {
                    //向服务段发送删除指令
                    $.post("/user/deleteUser", {userid: data.userid}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent == "edit") {
                openUpdateUser(data);
            } else if (layEvent == "resetUserPassword") {
                layer.confirm("<font color='red'>你真的要重置密码吗？</font>", function (index) {
                    //向服务段发送删除指令
                    $.post("/user/resetUserPassword", {userid: data.userid}, function (res) {
                        layer.msg(res.msg);
                    })
                });
            } else if (layEvent == 'selectUserRole') {
                openselectUserRole(data);
            }
        })

        //打开分配角色的弹出层
        function openselectUserRole(data) {
            var menuTree;
            mainIndex = layer.open({
                type: 1,
                title: '分配【' + data.realname + '】的角色',
                content: $("#selectUserRole"),
                area: ['800px', '500px'],
                btnAlign: 'c',
                btn: ['<div class="layui-icon layui-icon-release">确认分配</div>', '<div class="layui-icon layui-icon-close">取消分配</div>'],
                yes: function (index, layero) {
                    var checkStatus = table.checkStatus('roleTable');
                    var roleData = checkStatus.data;
                    var params = "userid=" + data.userid;
                    $.each(roleData, function (index, item) {
                        params += "&ids=" + item.roleid;
                    });
                    //保持角色和用户之间的关系
                    $.post("/user/saveUserRole", params, function (obj) {
                        layer.msg(obj.msg);
                    });
                    layer.close(mainIndex);
                },
                success: function (index) {
                    var roleTableIns = table.render({
                        elem: '#roleTable'   //渲染的目标对象
                        , url: '/user/initUserRole?userid=' + data.userid //数据接口
                        , title: '角色列表'//数据导出来的标题
                        , cellMinWidth: 100 //设置列的最小默认宽度
                        , cols: [[   //列表数据
                            {type: 'checkbox', fixed: 'left'}
                            , {field: 'roleid', title: 'ID', align: 'center'}
                            , {field: 'rolename', title: '角色名称', align: 'center'}
                            , {field: 'roledesc', title: '角色备注', align: 'center'}
                        ]]
                    })
                }
            });
        }

        //打开添加页面
        function openAddUser() {
            mainIndex = layer.open({
                type: 1,
                title: '添加用户',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '400px'],
                success: function (index) {
                    //由于添加与修改用的同一个弹出层，需要清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/user/addUser";
                }
            });
        }

        //打开更新页面
        function openUpdateUser(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改用户",
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '400px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "/user/updateUser";
                }
            });
        }

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('userTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (index, item) {
                if (index == 0) {
                    params = params + "ids=" + item.userid;
                } else {
                    params = params + "&ids=" + item.userid;
                }
            });
            layer.confirm('你真的要删除这些用户么？', function (index) {
                //向服务端发送删除指令
                $.post("/user/deleteBatchUser", params, function (result) {
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
