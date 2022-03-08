layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    $(".login-out").click(function (){
        $.removeCookie("userId",{domain:host,path:"/crm1"});
        $.removeCookie("userName",{domain:host,path:"/crm1"});
        $.removeCookie("trueName",{domain:host,path:"/crm1"});
        window.parent.location.href=ctx+"/index";
    })

});