<%--
  Created by IntelliJ IDEA.
  User: Lizbeth9421
  Date: 2021/7/28
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <script type="text/javascript" src="/layui/layui.js"></script>

</head>
<body>
<ul id="menuTree" class="dtree" data-id="0"></ul>
<script>
    layui.extend({
        dtree: '/layui_ext/dist/dtree'
    }).use(['dtree', 'layer', 'jquery'], function () {
        var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery;
        // 初始化树
        var menuDtree = dtree.render({
            elem: "#menuTree",
            url: "/menu/loadMenuLeftTreeJson",
            dataStyle: "layuiStyle",  //使用layui风格的数据格式
            dataFormat: "list",  //配置data的风格为list
            response: {message: "msg", statusCode: 0},  //修改response中返回数据的定义
        });
        //监听树的节点点击事件
        dtree.on("node(menuTree)",function (obj) {
            var id=obj.param.nodeId;
            window.parent.right.reloadTable(id);
        })
    });
</script>
</body>
</html>
