layui.use(['carousel','form', 'table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        carousel = layui.carousel,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    //图片轮播
    carousel.render({
        elem: '#test10'
        ,width: '100%'
        ,height: '260px'
        ,interval: 5000
    });


    /**
     * 营销机会列表展示
     */
    var tableIns = table.render({
        elem: '#ComList', // 表格绑定的ID
        url: ctx + '/buy/list', // 访问数据的地址
        cellMinWidth: 100,
        // page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        // toolbar: "#toolbarDemo",
        defaultToolbar: [ ],
        id: "saleChanceListTable",
        cols: [[
            // {type: "checkbox"},
            // {field: "id", title: '编号'},
            {field: 'imgAddr', title: '图片', align: 'center', templet: '#imgtmp'},
            {field: 'comName', title: '名称', align: "center"},
            {field: 'comPrice', title: '价格', align: 'center'},
            {field: 'comHousenum', title: '库存', align: 'center'},
            {field: 'comRemark', title: '简单说说', align: 'center'},
            {title: '操作', templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });


    //行内工具栏监听
    table.on("tool(saleChances)", function (data) {
        var layEvent = data.event;
        var id = data.data.id;
        if (layEvent === 'choose') {
            var id = "id=" + id;
            var url = ctx + "/buy/openChooseBurden" + "?" + id;
            //发送请求
            layui.layer.open({
                title:"<h3>添加配料</h3>",
                type: 2,//iframe
                content: url,
                area: ["550px", "550px"],
                maxmin: true
            });
        }
    });


    $('#shopcar').click(function (){
        //发送请求
        layui.layer.open({
            title:"<h3>我的购物车</h3>",
            type: 2,//iframe
            content: ctx + "/shopCar/openShopCar",
            area: ["437px", "600px"],
            maxmin: true
        });
    });
});