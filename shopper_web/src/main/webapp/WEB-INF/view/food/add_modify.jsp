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
    <title>添加修改商品</title>

    <script>
        $(function () {
            var food_image_url = "${path}/food/getFoodImage.do?shopperNickName=${sessionScope.shopper_nick_name.nick_name}&isScaleSmall=0";
            //失去焦点事件--只提交图片
            $("#food_image").change(function () {
                $("#food_image").attr("name","file");
                var formData = new FormData($("#food_form")[0]);
                var json = sendFormRequestJson(false,"${path}/food/saveImage.do",formData,"${path}/loginIndex.do")
                $("#food_image").removeAttr("name");
                if (json.code == "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添保存图片成功'});
                    var imageName = json.data;
                    $("#food_image_name").val(imageName);
                    $("#pre_image").attr("src",food_image_url + "&imageName=" + imageName);
                    $("#pre_image_div").show();
                    $("#lb_image").text("菜品图片预览");
                    $("#lb_image").css("color","");
                }else {
                    $("#topbar-collapse").popover({theme: 'danger',content: json.msg});
                }
                $("#topbar-collapse").click();
            });
            //食物表单提交按钮--不提交图片
            $("#sub_food_info").click(function () {
                var formData = new FormData($("#food_form")[0]);
                var json = sendFormRequestJson(false,"${path}/food/saveFood.do",formData,"${path}/loginIndex.do")
                if (json.code == "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添加/更新菜品成功'});
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
    <form class="am-form" id="food_form" enctype="multipart/form-data" method="post">
        <fieldset>
            <legend>新增/修改菜品</legend>

            <div class="am-form-group" style="display: none">
                <label for="food_id">菜品id</label>
                <input type="text" class="" name="id" id="food_id" value="${food.id}">
            </div>

            <div class="am-form-group">
                <label for="parent_food_id">菜品所属分类</label>
                <select id="parent_food_id" name="parent_food_id">
                    <option value="00">请选择</option>
                    <c:forEach items="${parentFoods}" var="parentFood">
                    <option value="${parentFood.id}" <c:if test="${parentFood.id == food.parent_food_id}">selected="selected"</c:if>>${parentFood.food_name}</option>
                    </c:forEach>
                </select>
                <span class="am-form-caret"></span>
            </div>

            <div class="am-form-group">
                <label for="food_name">菜品名</label>
                <input type="text" class="" name="food_name" id="food_name" placeholder="输入菜品名" value="${food.food_name}">
            </div>

            <div class="am-form-group">
                <label for="food_desc">菜品描述</label>
                <input type="text" class="" name="food_desc" id="food_desc" placeholder="输入菜品描述" value="${food.food_desc}">
            </div>
            <div class="am-form-group">
                <label for="food_price">菜品价格</label>
                <input type="text" class="" name="food_price" id="food_price" placeholder="设置菜品价格" value="${food.food_price}">
            </div>
            <div class="am-form-group">
                <label for="food_order">菜品排序</label>
                <input type="text" class="" name="food_order" id="food_order" placeholder="设置类别内的菜品排序,越大排序越后" value="${food.food_order}">
            </div>

            <div class="am-form-group am-form-file">
                <label for="food_image">菜品图片</label>
                <div>
                    <button type="button" class="am-btn am-btn-default am-btn-sm">
                        <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                </div>
                <input type="file" id="food_image">
            </div>

            <div class="am-form-group" <c:if test="${empty food}">style="display: none;"</c:if> id="pre_image_div">
                <label for="food_image_name" id="lb_image" <c:if test="${food == null || food.food_image_name == null || food.food_image_name == ''}">style="color: #b92c28"</c:if>>
                    <c:choose>
                        <c:when test="${food == null || food.food_image_name == null || food.food_image_name == ''}">
                            默认图片预览
                        </c:when>
                        <c:otherwise>
                            菜品图片预览
                        </c:otherwise>
                    </c:choose>
                </label>
                <input style="display: none;" readonly="readonly" type="text" class="" name="food_image_name" id="food_image_name" placeholder="图片服务器存储的名字">
                <img id="pre_image" <c:if test="${!empty food}">src="${path}/food/getFoodImage.do?shopperNickName=${sessionScope.shopper_nick_name.nick_name}&imageName=${food.food_image_name}&isScaleSmall=0" </c:if>>
            </div>

            <p><button type="button" class="am-btn am-btn-default" id="sub_food_info">提交</button></p>
        </fieldset>
    </form>
</div>

</body>
</html>
