<!DOCTYPE html>
<html>

<head>
    <title>订单管理</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">

<form class="layui-form" >

    <blockquote class="layui-elem-quote quoteBox">

        <form class="layui-form">

            <div class="layui-inline">

                <div class="layui-input-inline">
                    <input type="text" name="orderNum" class="layui-input searchVal" placeholder="请输入订单号" />
                </div>

                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>
                    搜索
                </a>

            </div>

        </form>

    </blockquote>

    <!--头部监听-->
    <table id="orderList" class="layui-table" lay-filter="orders"></table>
    <!--操作-->
    <script id="orderListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/order/order.js"></script>

</body>

</html>