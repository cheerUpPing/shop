<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加/修改微信公众号</title>

    <script>
        $(function () {
            var menu_id = "${menuId}";
            //编辑类别
            $(".cls_edit").bind("click",function () {
                var wx_id = $(this).attr("wx_id");
                var app_id = $(this).attr("app_id");
                var mch_id = $(this).attr("mch_id");
                var mch_key = $(this).attr("mch_key");
                $("#wx_id").val(wx_id);
                $("#app_id").val(app_id);
                $("#mch_id").val(mch_id);
                $("#mch_key").val(mch_key);
                $("#now_doing").text("您正处于编辑模式");
                $("#label_switch_model").show();
            });
            //切换为添加模式
            $("#label_switch_model").bind("click",function () {
                $("#wx_id").val("");
                $("#app_id").val("");
                $("#mch_id").val("");
                $("#mch_key").val("");
                $("#now_doing").text("您正处于添加模式");
                $("#label_switch_model").hide();
            });
            //删除微信公众号
            $(".cls_delete").bind("click",function () {
                var wx_id = $(this).attr("wx_id");
                //这里应该提示商户是否真的删除该类别
                var json = sendRequestJson("POST",false,"${path}/wx/deleteById.do",{wxId:wx_id},"${path}/loginIndex.do");
                if (json.code = "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添加/更新微信公众号成功'});
                    $("#li_menu_container a[menu_id='"+menu_id+"']").click();
                }else {
                    $("#topbar-collapse").popover({theme: 'danger',content: json.msg});
                }
                $("#topbar-collapse").click();
            });

            //食物表单提交按钮--不提交图片
            $("#sub_food_info").click(function () {
                var formData = new FormData($("#food_form")[0]);
                var json = sendFormRequestJson(false,"${path}/wx/saveWx.do",formData,"${path}/loginIndex.do")
                if (json.code == "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添加/更新微信公众号成功'});
                    //更新当前页面
                    $("#li_menu_container a[menu_id='"+menu_id+"']").click();
                }else {
                    $("#topbar-collapse").popover({theme: 'danger',content: json.msg});
                }
                $("#topbar-collapse").click();
            });
        });
    </script>
</head>
<body>
<div>
    <div class="am-scrollable-horizontal">
        <table class="am-table am-table-bordered am-table-hover am-table-compact am-text-nowrap">
            <thead>
            <tr>
                <th>app_id</th>
                <th>mch_id</th>
                <th>mch_key</th>
                <th>所属商户</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${!empty wxConfigList}">
                        <c:forEach items="${wxConfigList}" var="wxConfig" varStatus="status">
                            <tr>
                                <td>${wxConfig.app_id}</td>
                                <td>${wxConfig.mch_id}</td>
                                <td>${wxConfig.mch_key}</td>
                                <td>${wxConfig.shopper_nick_name}</td>
                                <td>
                                    <fmt:formatDate value="${wxConfig.add_time}" pattern="yyyy-MM-dd HH:mm:ss" var="result"/>${result}
                                </td>
                                <td>
                                    <!--下面的menu属性是 编辑微信公众号 的controller 菜单资源属性-->
                                    <button id="btn_edit" wx_id="${wxConfig.id}" app_id="${wxConfig.app_id}" mch_id="${wxConfig.mch_id}" mch_key="${wxConfig.mch_key}" class="am-btn am-btn-default am-btn-xs am-text-secondary cls_edit">编辑</button>
                                    <button id="btn_delete" wx_id="${wxConfig.id}" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only cls_delete">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" align="center"><label style="color: #ac2925">您当时还没有添加微信公众号配置,请在该页面添加</label></td>
                        </tr>
                    </c:otherwise>
                </c:choose>


            </tbody>
        </table>
    </div>
    <form class="am-form" id="food_form" method="post">
        <fieldset>
            <legend>新增/修改微信公众号 ---> <span id="now_doing" style="color: #be2924">您正处于添加模式</span> <a id="label_switch_model" style="display: none;" class="am-badge am-badge-success am-radius">切换为添加模式</a></legend>

            <div class="am-form-group" style="display: none">
                <label for="wx_id">app_id</label>
                <input type="text" class="" name="id" id="wx_id">
            </div>

            <div class="am-form-group">
                <label for="app_id">app_id</label>
                <input type="text" class="" name="app_id" id="app_id">
            </div>

            <div class="am-form-group">
                <label for="mch_id">mch_id</label>
                <input type="text" class="" name="mch_id" id="mch_id" placeholder="mch_id">
            </div>

            <div class="am-form-group">
                <label for="mch_key">mch_key</label>
                <input type="text" class="" name="mch_key" id="mch_key" placeholder="mch_key">
            </div>

            <p><button type="button" class="am-btn am-btn-default" id="sub_food_info">提交</button></p>
        </fieldset>
    </form>
</div>

</body>
</html>
