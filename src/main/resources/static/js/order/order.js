layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //角色列表展示
    var tableIns = table.render({
        elem: '#orderList',
        url : ctx+'/order/list1',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "orderListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'订单编号',fixed:"true", width:80},
            {field: 'orderNum', title: '订单编号', minWidth:50, align:"center"},
            {field: 'orderDate', title: '订单生成时间', minWidth:50, align:"center"},
            {field: 'userId', title: '用户ID', minWidth:100, align:'center'},
            {field: 'comSumPrice', title: '支付金额', align:'center',minWidth:150},
            {field: 'ordState', title: '订单状态', align:'center',minWidth:150,templet:function(d)
                {
                    return formatterState(d.ordState);
                }},
            {field: 'ordRemark', title: '订单备注', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#orderListBar',fixed:"right",align:"center"}
        ]]
    });

    /**
     * 格式化订单状态
     * 0 - 订单未支付
     * 1 - 订单已支付
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(ordState){
        if(ordState==0) {
            return "<div style='color: blue'>订单未支付</div>";
        } else if(ordState==1) {
            return "<div style='color: green'>订单已支付</div>";
        }
    }


    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("orderListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                orderNum: $("input[name='orderNum']").val()
            }
        })
    });

    //头工具栏事件
    table.on('toolbar(order)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateRoleDialog();
                break;
            case "grant":
                openAddGrantDailog(checkStatus.data);
                break;
        }
    });

    /**
     * 行监听
     */
    table.on("tool(orders)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openAddOrUpdateRoleDialog(obj.data.id);
        }
    });
    // 打开编辑页面
    function openAddOrUpdateRoleDialog(uid){
        var url = ctx+"/order/addOrUpdateOrderPage";
        // var title="订单管理-订单添加";
        if(uid){
            url = url+"?id="+uid;
            title="订单管理-订单更新";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","280px"],
            maxmin:true,
            content : url
        });
    }

    // //打开授权页面
    // function openAddGrantDailog(datas){
    //     if(datas.length==0){
    //         layer.msg("请选择待授权角色记录!", {icon: 5});
    //         return;
    //     }
    //     if(datas.length>1){
    //         layer.msg("暂不支持批量角色授权!", {icon: 5});
    //         return;
    //     }
    //     var url = ctx+"/order/toAddGrantPage?orderId="+datas[0].id;
    //     var title="角色管理-角色授权";
    //     layui.layer.open({
    //         title : title,
    //         type : 2,
    //         area:["600px","280px"],
    //         maxmin:true,
    //         content : url
    //     });
    // }

    /**
     * 订单编辑行监听
     */
    // table.on("tool(orders)", function(obj){
    //     var layEvent = obj.event;
    //     if(layEvent === "edit") {
    //         openAddOrUpdateRoleDialog(obj.data.id);
    //     }else if(layEvent === "del") {
    //         layer.confirm('确定删除当前角色？', {icon: 3, title: "角色管理"}, function (index) {
    //             $.post(ctx+"/order/delete",{id:obj.data.id},function (data) {
    //                 if(data.code==200){
    //                     layer.msg("操作成功！");
    //                     tableIns.reload();
    //                 }else{
    //                     layer.msg(data.msg, {icon: 5});
    //                 }
    //             });
    //         })
    //     }
    // });



});