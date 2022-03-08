<!DOCTYPE html>
<html>
<head>
    <title>点餐专区</title>
    <#include "../common.ftl">
    <style type="text/css">
        .layui-table img {
            max-width: 300px;
        }
        .layui-table-cell {
            height: auto !important;
            white-space: normal;
        }
        #shopcar{
            width:50px;
            height:2.8em;
            margin:0 auto;
            overflow:hidden;
            position: fixed;
            bottom:150px;
            right: 0;
            z-index: 1;
            display: inline-block;
            cursor: pointer;
            border:3px solid orange;
            border-radius: 15px;
        }
        #main{
            z-index: 0;
        }
    </style>
</head>
<body class="childrenBody" style="margin: 0px">
<#--<div><img src="../images/rexiao.png" height="249px" width="100%">-->
<#--</div>-->
<div class="layui-carousel" id="test10">
    <div carousel-item="">
        <div>
            <img src="../images/kfc1.png" width="100%">
        </div>
        <div>
            <img src="../images/kfc2.png" width="100%">
        </div>
        <div>
            <img src="../images/kfc3.png" width="100%">
        </div>
        <div>
            <img src="../images/kfc4.png" width="100%">
        </div>
        <div>
            <img src="../images/kfc5.png" width="100%">
        </div>
    </div>
</div>
<div>
    <img id="shopcar" src="../images/shopcar.png" width="50px">
</div>
<div id="main">
    <form class="layui-form">
        <!-- 数据表格 -->
        <table id="ComList" class="layui-table" lay-filter="saleChances">
        </table>
<#--        <script type="text/html" id="toolbarDemo">-->
<#--            <div id="buybtn" class="layui-btn-container">-->
<#--                <a class="layui-btn layui-btn-normal layui-btn-radius addNews_btn" lay-event="add">-->
<#--                    <i class="layui-icon">&#xe608;</i>-->
<#--                    添加-->
<#--                </a>-->
<#--            </div>-->
<#--        </script>-->
        <!--操作-->
        <script id="saleChanceListBar" type="text/html">
            <a class="layui-btn layui-btn-lg layui-btn-normal layui-btn-radius" id="edit" lay-event="choose">选择口味
            </a>
        </script>
    </form>
    <script type="text/javascript" src="${ctx}/js/buy/buy.js">
    </script>
    <script type="text/html" id="imgtmp">
        <img src="../images/{{d.imgAddr}}" style="width: 100%;height: 100%"/>
    </script>
</div>


</body>
</html>