layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addOrUpdateRole)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        //var url=ctx + "/order/save";
        url=ctx + "/order/update";
        $.post(url, data.field,function (res) {
            //alert(5555)
            if (res.code == 200) {
                //alert(6666);
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                },10 );
            } else {
                layer.msg(
                    res.msg, {
                        icon: 5
                    }
                );
            }
        });
        return false;
    });

    // var userId = $("input[name='id']").val();
    // formSelects.config('selectId',{
    //     type:"post",
    //     searchUrl:ctx+"/order/queryAllOrders?userId="+userId,
    //     keyName: 'orderNum', //自定义返回数据中name的key, 默认 name
    //     keyVal: 'id' //自定义返回数据中value的key, 默认 value
    // },true);

    /*取消*/
    $("#closeBtn").click(function (){
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

});