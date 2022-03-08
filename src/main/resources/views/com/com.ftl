<!DOCTYPE html>
<html>
<head>
    <title>商品管理</title>
    <#include "../common.ftl">
</head>
    <style>
        .layui-table-cell{
            height:80px;
            line-height: 50px;
        }

        .layui-table img {
            width: 250px;
            height: 90px;
        }
    </style>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="comName"
                           class="layui-input
							searchVal" placeholder="商品名称" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="comPrice" class="layui-input
							searchVal" placeholder="价格" />
                </div>
                <div class="layui-input-inline">
                    <select name="comState"  id="comState">
                        <option value="" >状态</option>
                        <option value="0">下架</option>
                        <option value="1" >在售</option>
                        <option value="2" >缺货</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="comList" class="layui-table"  lay-filter="com">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="comListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

    <script type="text/javascript" src="${ctx}/js/com/com.js"></script>

    <#--<script type="text/javascript" id="imgtmp">-->
    <#--    <img src="../images/com/{{ d.imgAddress}}" alt="图片丢失" style="width:200px;height:200px"></img>-->
    <#--</script>-->
</body>
</html>