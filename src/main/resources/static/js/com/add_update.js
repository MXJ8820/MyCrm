layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    var sid = 10000000;
    if ($("input[name='id']").val()){
        sid = $("input[name='id']").val();
        $.post(ctx+"/com/queryComState?id="+sid,function (data){

            for (var i = 0; i < 3; i++) {
                // 当前修改记录的指派人的值 与 循环到的值 相等，下拉框则选中
                if (data == i){
                    $("#comState").append('<option value="'+i+'"selected>'+formatterState(i)+'</option>');

                }else {
                    $("#comState").append('<option value="'+i+'">'+formatterState(i)+'</option>');
                }
                layui.form.render("select");
            }
        })
    }else {
        for (var i = 0; i < 3; i++) {
            // 当前修改记录的指派人的值 与 循环到的值 相等，下拉框则选中
                $("#comState").append('<option value="'+i+'">'+formatterState(i)+'</option>');
            layui.form.render("select");
        }
    }



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

    $("#uploadBtn").click(function (){

        //ajax
        var file = $("#input_file")[0].files[0];       //获取上传的文件

        var formData = new FormData();          //创建 FormData对象
        formData.append("file", file);          //append请求参数 file

        $.ajax({
            type:"post",
            url:ctx+"/com/upload",
            dataType: 'json',                 //返回的数据格式：json/xml/html/script/jsonp/text
            data: formData,
            contentType: false,               // 不设置Content-Type请求头  必须设为false
            processData: false,               // 不处理发送的数据          必须设为false
            success: function (result) {
                if (result.code==200){
                    layer.msg("上传成功");
                }else {
                    layer.msg("上传失败");
                }
            },

        })
        return false;
    })

    /**
     * 确认
     */
    form.on("submit(addOrUpdateCom)",function (obj){

        var fieldData = obj.field;

        var url = ctx+"/com/add";
        if ($("input[name='id']").val()){
            url = ctx+"/com/update";
        }

        $.ajax({
            type: "post",
            url:url,
            data:{
                id:$("input[name='id']").val(),
                comName:fieldData.comName,
                comPrice:fieldData.comPrice,
                comHousenum:fieldData.comHousenum,
                comRemark:fieldData.comRemark,
                imgAddr:$("input[name='imgAddr']").val(),
                comState:fieldData.comState
            },
            dataType: "json",
            success:function (result) {
                if (result.code == 200){
                    //提示成功
                    layer.msg("操作成功");

                    parent.location.reload();
                }else {
                    layer.msg(result.msg);
                }
            }
        })
        return false;
    })

    /**
     * 取消
     */
    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);

        parent.layer.close(index);

    });
})