<%--
  Created by IntelliJ IDEA.
  User: Lizbeth921
  Date: 2021/8/6
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理</title>
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
</head>
<body>
<!--搜索条件开始-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户姓名:</label>
            <div class="layui-input-inline">
                <input type="text" name="custname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户地址:</label>
            <div class="layui-input-inline">
                <input type="text" name="address" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户电话:</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户职位:</label>
            <div class="layui-input-inline">
                <input type="text" name="career" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">清空</button>
            <button type="button" class="layui-btn layui-icon layui-icon-export" id="doExport">导出</button>
        </div>
    </div>
</form>
<!--搜索条件结束-->
<!--数据表格开始-->
<table class="layui-hide" id="customerTable" lay-filter="customerTable"></table>
<div style="display: none;" id="customerToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div id="customerBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!--数据表格结束-->
<!--添加和修改的弹出层开始-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户姓名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="custname" lay-verify="required" placeholder="请输入客户姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">客户电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" placeholder="请输入客户电话" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" placeholder="请输入客户地址" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户职位:</label>
                <div class="layui-input-inline">
                    <input type="text" name="career" placeholder="请输入客户职位" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline ">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity" readonly="readonly" placeholder="请输入客户身份证号" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" checked="checked" title="男">
                    <input type="radio" name="sex" value="0" title="女">
                </div>
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
<!--添加和修改的弹出层结束-->
<script>
    layui.use(['jquery', 'layer', 'form', 'table'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        //渲染数据表格
        var tableIns = table.render({
            elem: '#customerTable',
            title: '用户数据表',
            url: '/customer/loadAllCustomer',
            cellMinWidth: 100, //设置列的最小默认宽度
            page: true,
            height: 'full-280',
            toolbar: '#customerToolBar',
            page: true,
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'identity', title: '身份证号', align: 'center', width: '250'},
                {field: 'custname', title: '客户姓名', align: 'center', width: '100'},
                {field: 'phone', title: '客户电话', align: 'center', width: '180'},
                {field: 'address', title: '客户地址', align: 'center', width: '380'},
                {field: 'career', title: '客户职位', align: 'center', width: '170'},
                {
                    field: 'sex', title: '性别', align: 'center', templet: function (d) {
                        return d.sex == '1' ? '<font class="layui-icon-male layui-icon" color=#7fffd4>男</font>' : '<font class="layui-icon layui-icon-female" color=red>女</font>';
                    }
                },
                {field: 'createtime', title: '录入时间', align: 'center', width: '250'},
                {fixed: 'right', title: '操作', toolbar: '#customerBar', width: 220, align: 'center'}
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
            //表单数据序列化
            var params = $("#searchFrm").serialize();
            tableIns.reload({
                url: "/customer/loadAllCustomer?" + params,
                page: {curr: 1}
            });
        });

        //导出数据
        $("#doExport").on('click', function () {
            //表单数据序列化
            var params = $("#searchFrm").serialize();
            window.location.href = "/stat/customerExport?" + params;
        });

        //监听头部工具条事件
        table.on("toolbar(customerTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddCustomer();
                    break;
                case 'deleteBatch' :
                    deleteBatch();
                    break;
            }
        });

        //监控行工具条事件
        table.on('tool(customerTable)', function (obj) {
            var data = obj.data;//获取当前行的数据
            var layEvent = obj.event;//获取lay-event对应的值
            if (layEvent == 'del') {
                layer.confirm("你真的要删除<font color='red'>" + data.custname + "</font>" + "这个客户吗？", function (index) {
                    //向服务段发送删除指令
                    $.post("/customer/deleteCustomer", {identity: data.identity}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent == "edit") {
                openUpdateRole(data);
            }
        })


        //打开添加页面
        function openAddCustomer() {
            mainIndex = layer.open({
                type: 1,
                title: '添加客户',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '300px'],
                success: function (index) {
                    //由于添加与修改用的同一个弹出层，需要清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/customer/addCustomer";
                }
            });
        }

        //打开更新页面
        function openUpdateRole(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改客户",
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '300px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    url = "/customer/updateCustomer";
                }
            });
        }

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('customerTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (index, item) {
                if (index == 0) {
                    params = params + "identities=" + item.identity;
                } else {
                    params = params + "&identities=" + item.identity;
                }
            });
            layer.confirm('你真的要删除这些客户么？', function (index) {
                //向服务端发送删除指令
                $.post("/customer/deleteBatchCustomer", params, function (result) {
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
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据表格
                tableIns.reload();
            });
            // $.ajax({
            //     type: "POST",
            //     url: url,
            //     data: params,
            //     async: true,
            //     success: function (obj) {
            //         layer.msg(obj.msg);
            //         //关闭弹出层
            //         layer.close(mainIndex);
            //         //刷新数据表格
            //         tableIns.reload();
            //     }
            // });
        });
    });
</script>
</body>
</html>