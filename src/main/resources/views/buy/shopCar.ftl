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
        <button class="layui-btn layui-btn-normal layui-btn-radius layui-btn-big" lay-event="getCheckData">下单</button>
<#--        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>-->
<#--        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>-->
    </div>
</script>
<script type="text/html" id="barDemo">
<#--    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
    <a class="layui-btn layui-btn-warm layui-btn-radius layui-btn-xs" lay-event="del">删除</a>
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
            ,url:ctx+'/shopCar/selectAllInShopCar'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: [ ]
            ,title: '我的购物车'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'markOrderNum', title:'markOrderNum', width:120, edit: 'text'}
                ,{field:'userId', title:'user_id', width:120, edit: 'text'}
                ,{field:'comName', title:'商品名称', width:120, edit: 'text'}
                ,{field:'sumPrice', title:'总价', width:80, edit: 'text'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,done:function (){
                $('[data-field="markOrderNum"]').css('display','none');
                $('[data-field="userId"]').css('display','none');
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    if (data.length == 0){
                        layer.msg("还没选择哦",{icon:5});
                        return ;
                    }
                    $.ajax({
                        type:"post",
                        url:ctx+"/shopCar/addOrderFromShopCar",
                        data:{data:JSON.stringify(data)},
                        success:function (msg){
                            if (msg.code == 200){
                                layer.msg("下单成功",{icon:6});
                                // 先得到当前iframe层的索引
                                var index = parent.layer.getFrameIndex(window.name);
                                // 再执行关闭
                                parent.layer.close(index);
                                //重新加载表格
                                tableIns.reload('tableIns');
                                //重新加载
                                parent.location.reload();
                            }else {
                                layer.msg(msg.msg,{icon:5});
                            }
                            // //重新加载表格
                            // table.reload('tableIns');
                        }
                    });
                    return ;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            console.log(data);
            console.log(data.comName);
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    //关闭对话框
                    layer.close(index);
                    //发送请求
                    $.ajax({
                        type:"post",
                        url: ctx + "/shopCar/delete",
                        data:{
                            markOrderNum:data.markOrderNum
                        },
                        success:function (msg){
                            if (msg.code == 200){
                                layer.msg("删除成功",{icon: 6});
                                //重新加载表格
                                tableIns.reload();
                            }else {
                                layer.msg(msg.msg,{icon:5});
                            }
                        }
                    });
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>

</body>
</html>