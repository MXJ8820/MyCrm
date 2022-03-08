layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 用户基本数据修改 表单提交
     */
    form.on("submit(saveBtn)",function(obj){

        //获取页面表单元素的内容
        var fieldData = obj.field;
        console.log(fieldData);

        //发送ajax对接后端用户基本数据修改接口实现修改操作
        $.ajax({
            type: "post",
            url: ctx + "/user/updateUser",
            data:{
                userName:fieldData.userName,
                phone:fieldData.phone,
                email:fieldData.email,
                trueName:fieldData.trueName,
                id:fieldData.id
            },
            dateType:"json",

            //接收后端传过来的信息(是否报错即是否修改成功)
            success:function (data){
                // 判断是否成功
                if(data.code==200){
                    //成功，提示信息
                    layer.msg("修改成功",{icon:1});
                    //跳转到登录页面 (父窗口跳转)
                    window.parent.location.href=ctx+"/index";
                }else {
                    //提示信息
                    layer.msg(data.msg);
                }
            }

        });

        //取消默认表单的跳转
        return false;

    });

});