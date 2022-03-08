layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#comList',
        url: ctx + '/com/listShow',
        cellMinWidth: 95,
        page: true,
        // full-125
        height: "full-125",
        limits: [5, 10, 15, 20],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "comListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "id", title: '编号', fixed: "true", width: 80},
            {field: 'comName', title: '商品名称', minWidth: 50, align: "center"},
            {field: 'comPrice', title: '商品价格', minWidth: 100, align: 'center'},
            {field: 'comHousenum', title: '商品库存', minWidth: 100, align: 'center'},
            {field: 'comRemark', title: '商品备注', align: 'center'},
            {field: 'imgAddr', title: '商品图片', align: 'center', minWidth: 150,templet:function (d){
                    // return "<img src = '"+"../images/com/"+d.imgAddr + "'height= '150px'  width = '200px'/>"
                    return "<img src = '"+imagePath+d.imgAddr + "'height= '150px'  width = '200px'/>"
                }},
            {field: 'comState', title: '分配状态', align:'center',templet: function(d){
                    return formatterState(d.comState);
                }},
            {title: '操作', minWidth: 150, templet: '#comListBar', fixed: "right", align: "center"}
        ]]
    });


    function formatterState(state){
        if(state==0) {
            return "<div style='color: yellow'>下架</div>";
        } else if(state==1) {
            return "<div style='color: green'>在售</div>";
        } else if (state==2){
            return "<div style='color: red'>缺货</div>";
        } else {
            return "<div style='color: red'>商品状态错误</div>";
        }
    }


    $(".search_btn").click(function (){
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                comName: $("input[name='comName']").val(),
                comPrice: $("input[name='comPrice']").val(),
                comState:$("#comState").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    /**
     * 头部工具栏
     */
    table.on('toolbar(com)',function (obj){
        //获取选中对象状态
        var checkStatus = table.checkStatus(obj.config.id);

        if(obj.event ==='add'){
            //alert("添加");
            openAddOrUpdateComDialog();
        }else if(obj.event ==='del'){
            //alert("dels");选中数据对象
            deleteCom(checkStatus.data);
        }

    });

    function deleteCom(data){
        //前端验证
        if(data.length==0){
            layer.msg("请选中数据啊?");
            return ;
        }
        //提示确定要是删除
        layer.confirm("你确定要删除这些数据吗?",{
            btn:["确认","取消"],
        },function (index){
            //关闭确认框
            layer.close(index);
            //收集收集
            var ids=[];
            //循环遍历
            for(var x in data){
                ids.push(data[x].id);
            }
            //发送ajax【1,2,3】
            $.ajax({
                type:"post",
                data:{"ids":ids.toString()},
                url:ctx+"/com/delete",
                dataType:"json",
                success:function(result){
                    //判断
                    if(result.code==200){
                        //刷新页面
                        tableIns.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon:5 });
                    }
                }

            });
        });
    }

    function openAddOrUpdateComDialog(id){
        var title = "<h2>商品信息 - 商品添加</h2>";
        var url = ctx + "/com/addOrUpdateComPage";
        // 通过id判断是添加操作还是修改操作
        if (id) {
            // 如果id不为空，则为修改操作
            title = "<h2>商品信息 - 商品更新</h2>";
            url = url + "?id=" + id;
        }
        layui.layer.open({
            title:title,
            type:2,
            content: url,
            area:["500px","620px"],
            maxmin:true
        });
    }

    /**
     * 行内工具栏
     */
    /**
     * 表格行 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(com)', function(obj){
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应

        // 判断事件类型
        if(layEvent === 'edit'){ // 编辑操作
            // 获取当前要修改的行的id
            var id = data.id;
            // 点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateComDialog(id);
        } else if (layEvent == "del") { // 删除操作
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"商品信息管理"},
                function (index) {
                    // 关闭窗口
                    layer.close(index);
                    // 发送ajax请求，删除记录
                    $.ajax({
                        type:"post",
                        url: ctx + "/com/delete",
                        data:{
                            ids:data.id
                        },
                        dataType:"json",
                        success:function (result) {
                            if (result.code == 200) {
                                // 加载表格
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });
        }
    });
})

