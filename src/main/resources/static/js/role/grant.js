var zTreeObj;
$(function () {
    //页面加载完毕，将资源模块信息存储到tree
    loadModuleInfo();
});

function loadModuleInfo() {
    //发送ajax查询资源信息
    $.ajax({
        type:"post",
        url:ctx+"/module/queryAllModules02",
        data:{
            roleId:$("#roleId").val()
        },
        dataType:"json",
        success:function (data) {
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: false
                    // showIcon: false
                },
                check: {
                    enable: true,
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            var zNodes =data;
            zTreeObj=$.fn.zTree.init($("#test1"), setting, zNodes);
        }
    })
}

function zTreeOnCheck(event, treeId, treeNode) {

    //拿到选中的节点
    var nodes= zTreeObj.getCheckedNodes(true);
    var roleId=$("#roleId").val();
    var mids="mids=";

    for(var i=0;i<nodes.length;i++){
        if(i<nodes.length-1){
            mids=mids+nodes[i].id+"&mids=";
        }else{
            mids=mids+nodes[i].id;
        }
    }

    $.ajax({
        type:"post",
        url:ctx+"/role/addGrant",
        data:mids+"&roleId="+roleId,
        dataType:"json",
        success:function (data) {
            console.log(data);
        }
    })

}