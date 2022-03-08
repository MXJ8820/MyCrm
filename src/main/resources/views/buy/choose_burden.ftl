<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <#include "../common.ftl">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<input type="hidden" name="id" value="${(id)!}">
<div class="layui-btn-container">
    <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"
            lay-demotransferactive="getData">添加至购物车</button>
    <button type="button" class="layui-btn layui-btn-warm layui-btn-radius"
            lay-demotransferactive="reload">重新选择</button>
</div>
<div id="test7" class="demo-transfer"></div>

<script type="text/javascript" src="${ctx}/js/buy/choose_burden.js" charset="utf-8">
</script>

</body>
</html>