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
    <div class="layui-btn-container">
<#--        <button class="layui-btn layui-btn-normal layui-btn-radius layui-btn-big" lay-event="getCheckData">下单</button>-->
        <#--        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>-->
        <#--        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>-->
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-warm layui-btn-radius layui-btn-xs" lay-event="del">退单</a>
</script>
<script type="text/html" id="barDemoGoods">
    <a class="layui-btn layui-btn-danger layui-btn-radius layui-btn-xs" lay-event="goods">查看商品</a>
</script>

<script type="text/javascript" src="${ctx}/js/buy/buy.js">
</script>
<script>
    layui.use(['form','table', 'layer'], function () {
        var layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table,
            layer = layui.layer,
            form = layui.form;



        var tableIns = table.render({
            elem: '#test'
            ,url:ctx+'/order/list'
            // ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: [ ]
            ,title: '我的购物车'
            ,cols: [[
                // {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'编号', width:170, edit: 'text'}
                ,{field:'orderNum', title:'订单编号', width:120, edit: 'text'}
                ,{field:'comSumPrice', title:'订单价格', width:120, edit: 'text'}
                ,{field:'ordRemark', title:'订单状态', width:120, edit: 'text'}
                ,{field:'orderDate', title:'下单时间', width:170, edit: 'text'}
                ,{fixed: 'right', title:'订单商品', toolbar: '#barDemoGoods', width:100}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:100}
            ]]
            ,done:function (){
                $('[data-field="id"]').css('display','none');
            }
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的不要了么', function(index){
                    //关闭对话框
                    layer.close(index);
                    //发送请求
                    $.ajax({
                        type:"post",
                        url:ctx+"/order/deleteOrder",
                        data:{id:data.id},
                        success:function (msg){
                            if (msg.code == 200){
                                layer.msg("退单成功",{icon: 6});
                                //重新加载表格
                                tableIns.reload();
                            }else {
                                layer.msg(msg.msg,{icon:5});
                            }
                        }
                    });
                });
            } else if(obj.event === 'goods'){
                //发送请求
                layui.layer.open({
                    title:"<h3>订单详情</h3>",
                    type: 2,//iframe
                    content:ctx+"/order/openOrderIncludeCom?id="+data.id,
                    area:["600px","500px"],
                    maxmin:true
                });
            }
        });
    });
</script>

</body>
</html>