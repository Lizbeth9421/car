<%--
  Created by IntelliJ IDEA.
  User: Lizbeth921
  Date: 2021/8/13
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查单管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrom=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="/favicon.ico">
<script src="/layui/layui.js"></script>
<link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="/css/public.css" media="all"/>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">检查单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkid" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出租单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentid" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">存在问题:</label>
                <div class="layui-input-inline">
                    <input type="text" name="problem" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开始时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" id="startTime" readonly="readonly" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">结束时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" id="endTime" readonly="readonly" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkdesc" autocomplete="off"
                           class="layui-input">
                </div>
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
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="checkTable" lay-filter="checkTable"></table>
<div style="display: none;" id="checkBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">检查时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkdate" id="checkdate" readonly="readonly" placeholder="请输入起租时间"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">操作员:</label>
                <div class="layui-input-inline">
                    <input type="text" name="opername" lay-verify="required" readonly="readonly" placeholder="请输入操作员"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">检查单号:</label>
            <div class="layui-input-block">
                <input type="text" name="checkid" id="checkid" readonly="readonly" placeholder="请输入检查单号"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出租单号:</label>
            <div class="layui-input-block">
                <input type="text" name="rentid" lay-verify="required" readonly="readonly" placeholder="请输入出租单号"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">赔付金额:</label>
                <div class="layui-input-inline">
                    <input type="text" name="paymoney" lay-verify="required" placeholder="请输入车牌号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-inline">
                    <input type="text" name="checkdesc" lay-verify="required" placeholder="请输入问题描述" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">存在问题:</label>
            <div class="layui-input-block">
                <input type="text" name="problem" lay-verify="required" placeholder="请输入身份证号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置
                </button>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->

<script>
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;

        //渲染时间选择器
        laydate.render({
            elem: '#startTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime'
        });

        laydate.render({
            elem: '#checkdate',
            type: 'datetime'
        });

        //渲染数据表格
        var tableIns = table.render({
            elem: '#checkTable',   //渲染的目标对象
            url: '/check/loadAllCheck', //数据接口
            title: '检查单数据表',//数据导出来的标题
            toolbar: "#checkToolBar",//表格的工具条
            height: 'full-220',
            cellMinWidth: 100, //设置列的最小默认宽度
            page: true,  //是否启用分页
            cols: [[   //列表数据
                {field: 'checkid', title: '检查单号', align: 'center', width: '280'},
                {field: 'rentid', title: '出租单号', align: 'center', width: '280'},
                {field: 'checkdate', title: '检查时间', align: 'center', width: '180'},
                {field: 'problem', title: '存在问题', align: 'center', width: '180'},
                {field: 'checkdesc', title: '问题描述', align: 'center', width: '180'},
                {field: 'paymoney', title: '赔付金额', align: 'center', width: '120'},
                {field: 'opername', title: '操作员', align: 'center', width: '120'},
                {field: 'createtime', title: '录入时间', align: 'center', width: '180'},
                {fixed: 'right', title: '操作', toolbar: '#checkBar', width: 100, align: 'center'}
            ]]
        });

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "/check/loadAllCheck?" + params,
                page: {
                    curr: 1
                }
            })
        });

        //监听行工具事件
        table.on('tool(checkTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'edit') { //编辑
                openUpdateCheck(data);
            }
        });

        var url, mainIndex;

        //打开修改页面
        function openUpdateCheck(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改检查单',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '400px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "/check/updateCheck";
                }
            });
        }

        //保存
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            });
        });
    });
</script>
</body>
</html>
