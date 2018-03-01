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
            var menu_id = "${menuId}";
            //编辑类别
            $(".cls_edit").bind("click",function () {
                var food_id = $(this).attr("food_id");
                var food_name = $(this).attr("food_name");
                var food_desc = $(this).attr("food_desc");
                var food_order = $(this).attr("food_order");
                $("#food_id").val(food_id);
                $("#food_name").val(food_name);
                $("#food_desc").val(food_desc);
                $("#food_order").val(food_order);
                $("#now_doing").text("您正处于编辑模式");
                $("#label_switch_model").show();
            });
            //切换为添加模式
            $("#label_switch_model").bind("click",function () {
                $("#food_id").val("");
                $("#food_name").val("");
                $("#food_desc").val("");
                $("#food_order").val("");
                $("#now_doing").text("您正处于添加模式");
                $("#label_switch_model").hide();
            });
            //删除类别
            $(".cls_delete").bind("click",function () {
                var food_id = $(this).attr("food_id");
                //这里应该提示商户是否真的删除该类别
                var json = sendRequestJson("POST",false,"${path}/food/deleteBigFoodById.do",{foodId:food_id},"${path}/loginIndex.do");
                if (json.code = "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添加/更新类别成功'});
                    $("#li_menu_container a[menu_id='"+menu_id+"']").click();
                }else {
                    $("#topbar-collapse").popover({theme: 'danger',content: json.msg});
                }
                $("#topbar-collapse").click();
            });

            //食物表单提交按钮--不提交图片
            $("#sub_food_info").click(function () {
                var formData = new FormData($("#food_form")[0]);
                var json = sendFormRequestJson(false,"${path}/food/saveBigFood.do",formData,"${path}/loginIndex.do")
                if (json.code == "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '添加/更新类别成功'});
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
                <th>类别名</th>
                <th>类别描述</th>
                <th>菜品排序</th>
                <th>所属商户</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${!empty foodList}">
                        <c:forEach items="${foodList}" var="everySmallFood" varStatus="status">
                            <tr>
                                <td>${everySmallFood.food_name}</td>
                                <td>${everySmallFood.food_desc}</td>
                                <td>${everySmallFood.food_order}</td>
                                <td>${everySmallFood.shopper_nick_name}</td>
                                <td>
                                    <fmt:formatDate value="${everySmallFood.add_time}" pattern="yyyy-MM-dd HH:mm:ss" var="result"/>${result}
                                </td>
                                <td>
                                    <!--下面的menu属性是 编辑菜品 的controller 菜单资源属性-->
                                    <button id="btn_edit" food_id="${everySmallFood.id}" food_name="${everySmallFood.food_name}" food_desc="${everySmallFood.food_desc}" food_order="${everySmallFood.food_order}" food_shopper_nick_name="${everySmallFood.shopper_nick_name}" class="am-btn am-btn-default am-btn-xs am-text-secondary cls_edit">编辑</button>
                                    <button id="btn_delete" food_id="${everySmallFood.id}" food_name="${everySmallFood.food_name}" food_desc="${everySmallFood.food_desc}" food_order="${everySmallFood.food_order}" food_shopper_nick_name="${everySmallFood.shopper_nick_name}" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only cls_delete">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" align="center"><label style="color: #ac2925">您当时还没有添加商品类别,请在该页面添加</label></td>
                            </tr>
                        </c:otherwise>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <form class="am-form" id="food_form" method="post">
        <fieldset>
            <legend>新增/修改类别 ---> <span id="now_doing" style="color: #be2924">您正处于添加模式</span> <a id="label_switch_model" style="display: none;" class="am-badge am-badge-success am-radius">切换为添加模式</a></legend>

            <div class="am-form-group" style="display: none">
                <label for="food_id">菜品id</label>
                <input type="text" class="" name="id" id="food_id">
            </div>

            <div class="am-form-group">
                <label for="food_name">类别名</label>
                <input type="text" class="" name="food_name" id="food_name" placeholder="输入类别名">
            </div>

            <div class="am-form-group">
                <label for="food_desc">类别描述</label>
                <input type="text" class="" name="food_desc" id="food_desc" placeholder="输入类别描述">
            </div>

            <div class="am-form-group">
                <label for="food_order">类别排序</label>
                <input type="text" class="" name="food_order" id="food_order" placeholder="设置类别的排序,越大排序越后">
            </div>

            <p><button type="button" class="am-btn am-btn-default" id="sub_food_info">提交</button></p>
        </fieldset>
    </form>
</div>

</body>
</html>
