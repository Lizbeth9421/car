<%--
  Created by IntelliJ IDEA.
  User: Lizbeth9421
  Date: 2021/7/28
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜单管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <link rel="stylesheet" href="/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="title" id="search" class="layui-input">
                <%--当form表单中只有一个input标签时按回车键将会自动将表单提交。可以再添加一个input标签 然后隐藏掉--%>
                <input type="text" id="" name="" style="display: none;" class="layui-input"/>
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">清空</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->


<!-- 数据表格开始 -->
<table class="layui-hide" id="menuTable" lay-filter="menuTable"></table>
<div style="display: none;" id="menuToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>
</div>
<div id="menuBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<script src="/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        //渲染数据表格
        tableIns = table.render({
            elem: '#menuTable'   //渲染的目标对象
            , url: '/menu/loadAllMenu' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#menuToolBar"   //表格的工具条
            , height: 'full-200'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', align: 'center', width: '80'}
                , {field: 'pid', title: '父节点ID', align: 'center', width: '100'}
                , {field: 'title', title: '菜单名称', align: 'center', width: '120'}
                , {field: 'href', title: '菜单地址', align: 'center', width: '220'}
                , {
                    field: 'spread', title: '是否展开', align: 'center', width: '100', templet: function (d) {
                        return d.spread == '1' ? '<font color=orange>展开</font>' : '<font color=red>不展开</font>';
                    }
                }
                , {field: 'target', title: 'TARGET', align: 'center', width: '100'}
                , {
                    field: 'icon', title: '菜单图标', align: 'center', width: '100', templet: function (d) {
                        return "<div class='layui-icon'>" + d.icon + "</div>";
                    }
                }
                , {
                    field: 'available', title: '是否可用', align: 'center', width: '100', templet: function (d) {
                        return d.available == '1' ? '<font color=#9acd32>可用</font>' : '<font color=red>不可用</font>';
                    }
                }
                , {fixed: 'right', title: '操作', toolbar: '#menuBar', width: 180, align: 'center'}
            ]],
            done: function (data, curr, count) {
                //不是第一页时如果当前返回的的数据为0那么就返回上一页
                if (data.data.length == 0 && count != 1 && couunt != 0) {
                    tableIns.reload({
                        page: {curr: curr = 1}
                    });
                }
            }
        })


        //按钮点击的模糊查询
        $("#doSearch").on("click", function () {
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "/menu/loadAllMenu?" + params,
                page: {curr: 1}
            })
        })
        //敲击回车的模糊查询
        $('#search').keydown(function (e) {
            if (e.keyCode == 13) {
                $("#doSearch").click();
            }
        })
    });


    function reloadTable(id) {
        tableIns.reload({
            url: "/menu/loadAllMenu?id=" + id
        })
    }
</script>
</body>
</html>
