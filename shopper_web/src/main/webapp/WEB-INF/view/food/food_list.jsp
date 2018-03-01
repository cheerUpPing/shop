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
    <title>商品列表</title>

    <script>
        $(function () {
            //编辑菜品
            $(".cls_edit").bind("click",function () {
                var menu_link = $(this).attr("menu_link");
                var menu_name = $(this).attr("menu_name");
                var menu_id = $(this).attr("menu_id");
                var food_id = $(this).attr("food_id");
                var selectMenuLink = "div_menu_" + menu_id;
                //增加div的内容并且显示
                var data = sendRequestPage("POST", false, menu_link, {menuId:menu_id,foodId:food_id}, "${path}/loginIndex.do");
                hideAndShowDiv(selectMenuLink);
                $("#" + selectMenuLink).append(data);
                //导航显示
                showAndColoredLi(menu_id);
            });
            //删除菜品
            $(".cls_delete").bind("click",function () {
                var food_id = $(this).attr("food_id");
                var menu_id = $(this).attr("menu_id");
                var json = sendRequestJson("POST",false,"${path}/food/deleteFoodById.do",{foodId:food_id});
                //重新刷新菜单列表数据
                console.info(json);
                if (json.code == "0000"){
                    $("#topbar-collapse").popover({theme: 'danger',content: '删除成功'});
                    $("#topbar-collapse").click();
                    $("#li_menu_container a[menu_id='"+menu_id+"']").click();
                }else {
                    $.message({
                        message: json.msg,
                        time: '2000',
                        type: 'warning',
                        showClose: false,
                        autoClose: true
                    });
                }
            });
        });
    </script>
</head>
<body>
<div class="am-scrollable-horizontal">
    <table class="am-table am-table-bordered am-table-hover am-table-compact am-text-nowrap">
        <thead>
        <tr>
            <th>菜品类别</th>
            <th>菜品图片</th>
            <th>菜品名</th>
            <th>菜品描述</th>
            <th>菜品单价</th>
            <th>菜品排序</th>
            <th>菜品销量</th>
            <th>所属商户</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${foodListMap}" var="entry">
                <c:forEach items="${entry.value}" var="everySmallFood" varStatus="status">
                    <tr>
                        <c:if test="${status.index == 0}">
                            <td rowspan="${fn:length(entry.value)}" class="am-text-middle am-text-center">${entry.key.food_name}</td>
                        </c:if>
                        <td>
                            <%--<img src="${path}/shopper/getFoodImage.do?shopperNickName=${everySmallFood.shopper_nick_name}&imageName=${everySmallFood.food_image_name}&isScaleSmall=0">--%>
                        </td>
                        <td>${everySmallFood.food_name}</td>
                        <td>${everySmallFood.food_desc}</td>
                        <td>${everySmallFood.food_price}</td>
                        <td>${everySmallFood.food_order}</td>
                        <td>${everySmallFood.food_sale}</td>
                        <td>${everySmallFood.shopper_nick_name}</td>
                        <td>
                            <fmt:formatDate value="${everySmallFood.add_time}" pattern="yyyy-MM-dd HH:mm:ss" var="result"/>${result}
                        </td>
                        <td>
                            <!--下面的menu属性是 编辑菜品 的controller 菜单资源属性-->
                            <button id="btn_edit" food_id="${everySmallFood.id}" menu_name="${menuLink.menu_name}" menu_link="${path}/${menuLink.menu_url}" menu_id="${menuLink.id}" class="am-btn am-btn-default am-btn-xs am-text-secondary cls_edit">编辑</button>
                            <button id="btn_delete" food_id="${everySmallFood.id}" menu_id="${menuId}" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only cls_delete">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
