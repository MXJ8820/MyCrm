<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <#include "../common.ftl">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>


<script type="text/html" id="toolbarDemo">
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-warm layui-btn-radius layui-btn-xs" lay-event="ingredients">配料</a>
</script>
<script type="text/html" id="barDemoGoods">
    <a class="layui-btn layui-btn-danger layui-btn-radius layui-btn-xs" lay-event="goods">查看商品</a>
</script>

<script type="text/javascript" src="${ctx}/js/buy/buy.js">
</script>
<script>
    layui.use(['form', 'table', 'layer'], function () {
        var layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table,
            layer = layui.layer,
            form = layui.form;


        var tableIns = table.render({
            elem: '#test'
            , url: ctx + '/order/selectIngredientsByOrderComId'
            // , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: []
            , cols: [[
                , {field: 'asyName', title: '全部配料',width:200, edit: 'text'}
            ]]
        });


    });


</script>

</body>
</html>