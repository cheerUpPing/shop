<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品/菜单列表</title>
    <script type="text/javascript" src="${path}/bootstrap/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${path}/bootstrap/js/bootstrap.js"></script>
    <link href="${path}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${path}/icon_font/iconfont.css" rel="stylesheet"/>
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        #wrap {
            position: relative;
            width: 100%;
            min-height: 100%;
        }

        .left_menu {
            position: fixed;
            z-index: 1; /*关键*/
            top: 0;
            box-sizing: border-box;
            width: 100px;
            height: 100%;
            background: #2e6570 bottom;
        }

        .right_menu {
            box-sizing: border-box;
            float: left;
            margin-left: 105px;
            overflow-y: auto;
            overflow-x: hidden;
            clear: both;
            color: #717592;
            min-height: 500px;
        }

        strong {
            font-size: 16px;
            font-weight: bold;
        }

        #every_food {
            margin-bottom: 5px;
        }

        #image_div {
            float: left;
        }

        #info_div {
            float: left;
            margin-left: 5px;
        }

        .clear {
            clear: both;
        }

        #car_wrapper {
            position: fixed;
            bottom: 0;
            z-index: 2; /*关键*/
            right: 5px;
        }
    </style>
    <script type="text/javascript">
        //保存菜品数据 foodId_prince : 具体数据
        var all_data = new Map();

        //菜品操作面板
        function deleteFood(self_) {
            var food_id = self_.attr("food_id");
            var food_price = self_.attr("food_price");
            var big_food_id = self_.attr("big_food_id");
            var everyKey = food_id + "_" + food_price;
            var last_sum_price = parseFloat($("#sum_price").text());
            var last_good_num = parseInt($("#good_num").text());
            last_sum_price = last_sum_price - parseFloat(food_price);
            last_good_num = last_good_num - 1;
            all_data.delete(everyKey);
            $("#sum_price").text(last_sum_price);
            $("#good_num").text(last_good_num);
            $("#pup_sum_price").text(last_sum_price);
            //去掉菜品单选框选中
            $("input[food_id_price='" + everyKey + "']").prop("checked", false);
            //删除这一行
            self_.parent().parent().remove();
        }

        $(function () {
            //绑定事件
            $("#menus li").click(function () {
                $("#menus li").removeClass("active");
                $(this).addClass("active");
            });
            //价格标签绑定事件
            $("#info_div table input[type='checkbox']").change(function () {
                //未选中--选中
                var shopper_nick_name = $(this).attr("shopper_nick_name");
                var food_id = $(this).attr("food_id");
                var food_name = $(this).attr("food_name");
                var food_price = $(this).attr("food_price");
                var big_food_id = $(this).attr("big_food_id");
                var big_food_name = $(this).attr("big_food_name");
                var everyVal = {
                    "shopper_nick_name": shopper_nick_name,
                    "food_id": food_id,
                    "food_name": food_name,
                    "food_price": food_price,
                    "big_food_id": big_food_id,
                    "big_food_name": big_food_name
                };
                var everyKey = food_id + "_" + food_price;
                var last_sum_price = parseFloat($("#sum_price").text());
                var last_good_num = parseInt($("#good_num").text());
                if ($(this).is(":checked")) {//选中
                    last_sum_price = last_sum_price + parseFloat(food_price);
                    last_good_num = last_good_num + 1;
                    all_data.set(everyKey, everyVal);
                } else {//选中--未选中
                    last_sum_price = last_sum_price - parseFloat(food_price);
                    last_good_num = last_good_num - 1;
                    all_data.delete(everyKey);
                }
                $("#sum_price").text(last_sum_price);
                $("#good_num").text(last_good_num);
                //alert(shopper_nick_name + " " + food_id + " " + food_name + " " + price + "　" + big_food_id + " " + big_food_sort + "数组长度:" + all_data.length);
            });
            //点击购物车,弹出菜品列表
            $("#car_wrapper").click(function () {
                var food_container = $("<div class='table-responsive'></div>");//创建div
                var food_table = $("<table class='table table-bordered'></table>");
                food_table.appendTo(food_container);
                var food_thead = $("<thead></thead>");
                food_thead.appendTo(food_table);
                var food_tbody = $("<tbody></tbody>");
                food_tbody.appendTo(food_table);
                //设置标题
                var titles = ["菜品", "单价", "数量", "合计", "操作"];
                var everyTr = $("<tr></tr>");
                everyTr.appendTo(food_thead);
                for (var i = 0; i < titles.length; i++) {
                    var everyTd = $("<td>" + titles[i] + "</td>");
                    everyTd.appendTo(everyTr);
                }
                //具体菜品信息
                all_data.forEach(function (val, key, mapObj) {
                    var everyTr_ = $("<tr></tr>");
                    everyTr_.appendTo(food_tbody);
                    var everyTd_food_name = $("<td>" + val.food_name + "</td>");
                    everyTd_food_name.appendTo(everyTr_);
                    var everyTd_price = $("<td>" + val.food_price + "</td>");
                    everyTd_price.appendTo(everyTr_);
                    var everyTd_num = $("<td>" + 1 + "</td>");
                    everyTd_num.appendTo(everyTr_);
                    var everyTd_sum_price = $("<td>" + (1 * val.food_price) + "</td>");
                    everyTd_sum_price.appendTo(everyTr_);
                    var food_id = val.food_id;
                    var food_price = val.food_price;
                    var big_food_id = val.big_food_id;
                    var everyTd_delete = $("<td><button class='btn btn-default' onclick='deleteFood($(this))' food_id='" + food_id + "' food_price='" + food_price + "' big_food_id='" + big_food_id + "'>删除</button></td>");
                    everyTd_delete.appendTo(everyTr_);
                });
                var sum_price_tr = $("<tr></tr>");
                sum_price_tr.appendTo(food_tbody);
                var sum_price_data = parseFloat($("#sum_price").text());
                var sum_price_td = $("<td colspan='5' align='right' style='padding-right:15px;'>合计:&nbsp;<label id='pup_sum_price' style='color: red'>" + sum_price_data + "</label>&nbsp元</td>")
                sum_price_td.appendTo(sum_price_tr);
                $("#model_body").empty();
                food_container.appendTo("#model_body");
            });
            //支付按钮
            $("#sure_pay").click(function () {

            });
        });
    </script>
</head>
<body>
<div id="wrap">
    <c:choose>
        <c:when test="${!empty foodListMap}">
            <div class="left_menu">
                <ul class="nav nav-pills nav-stacked" id="menus">
                    <c:forEach items="${foodListMap}" var="entry" varStatus="index">
                        <li role="presentation" <c:if test="${index.index == 0}">class="active"</c:if>><a
                                href="#food_${entry.key.id}">${entry.key.food_name}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="right_menu">
                <c:forEach items="${foodListMap}" var="entry" varStatus="index">
                    <!--这里是具体的商品大类信息-->
                    <div class="nav nav-tabs" id="food_${entry.key.id}">
                        <strong>${entry.key.food_name}</strong>
                        <c:forEach items="${entry.value}" var="food">
                            <div id="every_food">
                                <div id="image_div">
                                    <img class="img-thumbnail"
                                         src="${path}/food/getFoodImage.do?shopperNickName=${shopperNickName}&imageName=${food.food_image_name}&isScaleSmall=0">
                                </div>
                                <div id="info_div">
                                    <table>
                                        <tr>
                                            <td><label>${food.food_name}</label></td>
                                        </tr>
                                        <tr>
                                            <td><label>销量:${food.food_sale}件</label></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <c:forEach items="${fn:split(food.food_price,'-')}" var="price">
                                                    <input type="checkbox" shopper_nick_name="${shopperNickName}"
                                                           big_food_id="${entry.key.id}"
                                                           big_food_name="${entry.key.food_name}" food_id="${food.id}"
                                                           food_name="${food.food_name}"
                                                           food_price="${price}"
                                                           food_id_price="${food.id}_${price}"
                                                           name="${food.food_name}"
                                                    >￥${price}
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            <!--购物车-->
            <div id="car_wrapper" data-toggle="modal" data-target="#myModal">
                    <%--<span>商品数:<label id="good_num">0</label></span>&nbsp;&nbsp;<span>合计:<label id="sum_price" style="color: crimson">0</label>元<button
                        class="btn-success">结算</button></span>--%>
                <i class="iconfont icon-gouwuche-copy" style="font-size: 50px;float: right; margin-right: 10px"></i>
                <label id="good_num"
                       style="position: absolute; color: red; font-size: 15px; float: right;top:0;right: 10px;">0</label>
                <div style="position: absolute; color: red; font-size: 25px; float: right;top:0;right: 30px;"><label
                        id="sum_price">0</label>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <h1>该商店目前没有任何商品/菜品</h1>
        </c:otherwise>
    </c:choose>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">菜单详情</h4>
            </div>
            <div class="modal-body" id="model_body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="sure_pay">确认支付</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
