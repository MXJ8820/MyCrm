<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置商品的ID -->
    <input type="hidden" name="id" value="${(com.id)!}">
    <input type="hidden" name="state" value="${(com.comState)!}">

<#--    &lt;#&ndash;设定营销机会指派人的id&ndash;&gt;-->
<#--    <input type="hidden" name="assignMan" id="man" value="${(com.comName)!}">-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="comName" id="comName"  value="${(com.comName)!}" placeholder="请输入商品名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="comPrice"
                   id="price" value="${(com.comPrice)!}" placeholder="请输入商品价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">库存量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="comHousenum"
                   lay-verify="required"  value="${(com.comHousenum)!}" placeholder="请输入库存量">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="comRemark"
                   name="comRemark" value="${(com.comRemark)!}" id="remark" placeholder="添加备注">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">图片上传</label>
        <div class="layui-input-block">
<#--            <textarea placeholder="请上传图片" name="imgAddr" class="layui-textarea">${(com.imgAddr)!}</textarea>-->

            <div class="layui-upload">
<#--                <button placeholder="请上传图片" name="uploadimg" class="layui-btn" id="uploadimg">上传图片</button>-->
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="preview">
                    <input type="file" name="file" id = "input_file" value="${(img_address)!}" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" οnchange="imgPreview(this)" >
                </div>
                <button type="button" class="layui-btn" id = "uploadBtn">上传</button>
<#--                <div style="width: 95px;">-->
<#--                    <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">-->
<#--                        <div class="layui-progress-bar" lay-percent=""></div>-->
<#--                    </div>-->
<#--                </div>-->
            </div>
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">-->
    <#--    <label class="layui-form-label">商品状态</label>-->
    <#--    <div class="layui-input-block">-->
    <#--        <select name="comState" id="comState">-->
    <#--            <option value="" selected>请选择状态</option>-->
    <#--            <option value="0">下架</option>-->
    <#--            <option value="1" >在售</option>-->
    <#--            <option value="2" >缺货</option>-->
    <#--        </select>-->
    <#--    </div>-->
    <#--</div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品状态</label>
        <div class="layui-input-block">
            <select name="comState" id="comState" value="${(com.ComState)!}">
                <option value="">请选择商品状态</option>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCom">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/com/add_update.js"></script>
</body>
</html>