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
<input type="hidden" name="id" value="${id}">
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
            , url: ctx + '/order/selectComByOrder'
            // , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: []
            , title: '我的购物车'
            , cols: [[
                , {field: 'comName', title: '全部商品', width: 130, edit: 'text'}
                , {field: 'id', title: '编号', width: 130, edit: 'text'}
                , {fixed: 'right', title: '查看配料', toolbar: '#barDemo', width: 130}
            ]]
            , done: function () {
                $('[data-field="id"]').css('display', 'none');
            }
        });


        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            console.log(data);
            console.log(data.comName);
            if (obj.event === 'ingredients') {
                //发送请求
                layui.layer.open({
                    title:"<h3>配料详情</h3>",
                    type: 2,//iframe
                    content:ctx+"/order/openOrderIncludeIngredients?id="+data.id,
                    area:["200px","220px"],
                    maxmin:true
                });
            }
        });
    });
</script>

</body>
</html>