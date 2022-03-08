layui.use(['transfer', 'layer', 'util'], function () {
    var $ = layui.$
        , transfer = layui.transfer
        , layer = layui.layer
        , util = layui.util;

    //模拟数据
    // var data2 = [
    //     {"value": "1", "title": "瓦罐汤"}
    //     , {"value": "2", "title": "油酥饼"}
    //     , {"value": "3", "title": "炸酱面"}
    //     , {"value": "4", "title": "串串香", "disabled": true}
    //     , {"value": "5", "title": "豆腐脑"}
    //     , {"value": "6", "title": "驴打滚"}
    //     , {"value": "7", "title": "北京烤鸭"}
    //     , {"value": "8", "title": "烤冷面"}
    //     , {"value": "9", "title": "毛血旺", "disabled": true}
    //     , {"value": "10", "title": "肉夹馍"}
    //     , {"value": "11", "title": "臊子面"}
    //     , {"value": "12", "title": "凉皮"}
    //     , {"value": "13", "title": "羊肉泡馍"}
    //     , {"value": "14", "title": "冰糖葫芦", "disabled": true}
    //     , {"value": "15", "title": "狼牙土豆"}
    // ]

    var data2 = undefined;
    $.ajax({
        type:"post",
        url: ctx + "/asy/list",
        dataType:"json",
        success:function(obj){
            data2 = obj;
            //实例调用
            transfer.render({
                elem: '#test7'
                ,data: data2
                ,id: 'key123' //定义唯一索引
                ,title: ['全部配料', '我选择的']
                ,showSearch: true
            })
            //批量办法定事件
            util.event('lay-demoTransferActive', {
                getData: function(othis){
                    var getData = transfer.getData('key123'); //获取右侧数据
                    if (getData.length == 0){
                        layer.msg("还没有选择配料哦",{icon:5});
                        return ;
                    }
                    layer.msg("添加成功",{icon:6});
                    // 先得到当前iframe层的索引
                    var index = parent.layer.getFrameIndex(window.name);
                    // 再执行关闭
                    parent.layer.close(index);
                    $.ajax({
                        type: "post",
                        url:ctx+"/shopCar/addToShopCar",
                        data:{
                            comId:$("input[name='id']").val(),
                            asys:JSON.stringify(getData)
                        },
                        dataType: "json",
                        // contentType:"application/x-www-form-urlencoded"
                        // success:function (obj){
                        //     if (obj.code==200){
                        //         layer.msg(obj.msg,{icon: 6});
                        //         // 先得到当前iframe层的索引
                        //         var index = parent.layer.getFrameIndex(window.name);
                        //         // 再执行关闭
                        //         parent.layer.close(index);
                        //     }else {
                        //         layer.msg(obj.msg,{icon: 5});
                        //     }
                        // }
                    });
                }
                ,reload:function(){
                    //实例重载
                    transfer.reload('key123', {
                        title: ['全部配料', '我选择的']
                        ,value: []
                        ,showSearch: true
                    })
                }
            });
        }
    });







});