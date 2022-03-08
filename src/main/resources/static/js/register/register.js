
layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    $("#sendEmailBtn").click(function (){
        console.log($("#email").val());
        if (!$("#email").val()){
            layer.msg("请输入邮箱,否则无法发送验证码");
            return ;
        }
        $.ajax({
            type:"post",
            url:ctx+"/email/sendEmail",
            data:{toEmail:$("#email").val()},

            success:function (result){
                if (result.code==200){
                    layer.msg("发送成功",function (){
                        // $.cookie("code",result.result);
                    });
                }else {
                    layer.msg(result.msg);
                }
            }
        })
        return false;
    })

    form.on("submit(registerBtn)",function (data){
        var fieldData = data.field;

        var url = ctx+"/user/reg_addUser";

        if (fieldData.again_password != fieldData.new_password){
            layer.msg("密码与确认密码不一致");
            return;
        }

        $.ajax({
            type: "post",
            url:url,
            data:{
                userName:fieldData.userName,
                userPwd:fieldData.new_password,
                trueName:fieldData.trueName,
                email:fieldData.email,
                code:fieldData.code,
                phone:fieldData.phone
            },
            dataType:"json",
            success:function (result){
                if (result.code === 200){
                    layer.msg("注册成功,请重新登录");

                    window.location.href=ctx+"/index";
                }else {
                    layer.msg(result.msg);
                }
            }

        })
        return false;
    });


    $("#closeBtn").click(function (){

        window.location.href=ctx+"/index";

        return false;

    })
});