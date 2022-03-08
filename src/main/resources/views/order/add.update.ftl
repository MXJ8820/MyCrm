<!DOCTYPE html>
<html>

    <head>
        <#include "../common.ftl">
    </head>

    <body class="childrenBody">

        <form class="layui-form" style="width:80%;">

            <input name="id" type="hidden" value="${(order.id)!}"/>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">订单状态</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input ordState" lay-verify="required" name="ordState" id="ordState" value="${(order.ordState)!}" placeholder="请输入订单状态">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">

                <label class="layui-form-label">订单备注</label>

                <div class="layui-input-block">
                    <input type="text" class="layui-input ordRemark" lay-verify="required" name="ordRemark" id="ordRemark" value="${(order.ordRemark)!}" placeholder="请输入订单备注">
                </div>

            </div>

            <br/>

            <div class="layui-form-item layui-row layui-col-xs12">

                <div class="layui-input-block">

                    <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateRole">确认</button>

                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>

                </div>

            </div>

        </form>

        <script type="text/javascript" src="${ctx}/js/order/add.update.js"></script>

    </body>

</html>