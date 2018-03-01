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
    <title>订单列表</title>
    <style>
        .elon_table
        {
            width: 100%;
            padding: 0;
            margin: 0;
        }
        .elon_table th {
            font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #4f6b72;
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            border-top: 1px solid #C1DAD7;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-align: left;
            padding: 6px 6px 6px 12px;
            background: #CAE8EA no-repeat;
        }
        .elon_table td {
            border-left: 1px solid #C1DAD7;
            font-size:14px;
            padding: 1px 6px 1px 5px;
            color: #4f6b72;
            margin: 0px;
        }
        .elon_table td.alt {
            background: #F5FAFA;
            color: #797268;
        }
        .elon_table th.spec,.elon_table td.spec {
            border-left: 1px solid #C1DAD7;
        }
    </style>
    <script>
        $(function () {
            //展开事件
            $(".btn_open").bind("click",function () {
                var isToOpen = parseInt($(this).attr("isToOpen"));//0执行展开动作 1执行隐藏动作
                var parent_order_no = "tr_" + $(this).attr("parent_order_no");
                if (isToOpen == 0){
                    $(this).attr("isToOpen","1");//下一次点击动作 就是隐藏动作了
                    $(this).text("隐藏");
                    $(this).parent().parent().siblings("."+ parent_order_no).fadeIn();
                }else if (isToOpen == 1){
                    $(this).attr("isToOpen","0");
                    $(this).text("展开");
                    $(this).parent().parent().siblings("."+ parent_order_no).hide();
                }
            });
            //子菜单完成事件
            $(".btn_complete").bind("click",function () {
               var children_order_no = $(this).attr("children_order_no");
               var parent_order_no = $(this).attr("parent_order_no");
               var parent_is_complete_label = "parent_is_complete_" + parent_order_no;
               var json = sendRequestJson("POST",false,"${path}/order/completeOrderDetail.do",{childrenOrderNo:children_order_no,parentOrderNo:parent_order_no},"${path}/loginIndex.do");
               if (json.code == "0000"){//完成了这个子菜单
                   $(this).parent().prev().children().removeClass("am-text-warning");
                   $(this).parent().prev().children().addClass("am-link-muted");
                   $(this).parent().prev().children().text("已完成");
                   $(this).hide();
               }else if (json.code == "0010"){//所有的子菜单都完成了
                   $(this).parent().prev().children().removeClass("am-text-warning");
                   $(this).parent().prev().children().addClass("am-link-muted");
                   $(this).parent().prev().children().text("已完成");
                   //更改父菜单为完成状态
                   $("#" + parent_is_complete_label).removeClass("am-text-warning");
                   $("#" + parent_is_complete_label).addClass("am-link-muted");
                   $("#" + parent_is_complete_label).text("已完成");
                   $(this).hide();
               }else {
                   $("#topbar-collapse").popover({theme: 'danger',content: json.msg});
                   $("#topbar-collapse").click();
               }
            });

        });
    </script>
</head>
<body>
    <div class="am-scrollable-horizontal">
        <div id="">

        </div>
        <table class="am-table am-table-bordered am-table-hover am-table-compact am-text-nowrap">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>微信订单号</th>
                <th>桌号</th>
                <th>总金额</th>
                <th>是否打包</th>
                <th>支付状态</th>
                <th>是否完成</th>
                <th>所属商户</th>
                <th>支付时间</th>
                <th>订单时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${!empty orderTradeListMap}">
                        <c:forEach items="${orderTradeListMap}" var="entry">
                            <tr>
                                <td>${entry.key.order_no}</td>
                                <td>${entry.key.wx_order_no}</td>
                                <td>${entry.key.seat_no}</td>
                                <td>${entry.key.sum_money}</td>
                                <td>
                                    <c:if test="${entry.key.is_packed == 0}">
                                       <label class="am-text-success">打包</label>
                                    </c:if>
                                    <c:if test="${entry.key.is_packed == 1}">
                                        <label class="am-link-muted">不打包</label>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${entry.key.pay_status == 0}">
                                        <label class="am-text-danger">未支付</label>
                                    </c:if>
                                    <c:if test="${entry.key.pay_status == 1}">
                                        <label class="am-link-muted">已支付</label>
                                    </c:if>
                                    <c:if test="${entry.key.pay_status == 2}">
                                        <label class="am-text-danger">支付失败</label>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${entry.key.is_complete == 0}">
                                        <label id="parent_is_complete_${entry.key.order_no}" class="am-link-muted">已完成</label>
                                    </c:if>
                                    <c:if test="${entry.key.is_complete == 1}">
                                        <label id="parent_is_complete_${entry.key.order_no}" class="am-text-warning">未完成</label>
                                    </c:if>
                                </td>
                                <td>${entry.key.shopper_nick_name}</td>
                                <td>
                                    <fmt:formatDate value="${entry.key.pay_time}" pattern="yyyy-MM-dd HH:mm:ss" var="result"/>${result}
                                </td>
                                <td>
                                    <fmt:formatDate value="${entry.key.order_time}" pattern="yyyy-MM-dd HH:mm:ss" var="result"/>${result}
                                </td>
                                <td style="width: 100px;">
                                    <!--下面的menu属性是 编辑菜品 的controller 菜单资源属性-->
                                    <button isToOpen="0" parent_order_no="${entry.key.order_no}" class="am-btn am-btn-default am-btn-xs am-text-secondary btn_open">展开</button>
                                </td>
                            </tr>
                            <!--循环子订单-->
                            <c:forEach items="${entry.value}" var="OrderTradeDetail">
                                <tr class="tr_${entry.key.order_no}" parent_order_no="${entry.key.order_no}" children_order_no="${OrderTradeDetail.children_order_no}" style="padding-right: 50px;display: none;">
                                    <td colspan="11">
                                        <div style="float: right">
                                            <table class="elon_table">
                                                <tbody>
                                                    <tr>
                                                        <td>${OrderTradeDetail.food_name}</td>
                                                        <td>单价:${OrderTradeDetail.single_prince}</td>
                                                        <td>数量:${OrderTradeDetail.bug_num}</td>
                                                        <td>总价:${OrderTradeDetail.sum_money}</td>
                                                        <td id="td_is_complete">
                                                            <c:if test="${OrderTradeDetail.is_complete == 0}">
                                                                <label id="order_detail_label" class="am-link-muted">已完成</label>
                                                            </c:if>
                                                            <c:if test="${OrderTradeDetail.is_complete == 1}">
                                                                <label id="order_detail_label" class="am-text-warning">未完成</label>
                                                            </c:if>
                                                        </td>
                                                        <td style="width: 100px;">
                                                            <button parent_order_no="${entry.key.order_no}" children_order_no="${OrderTradeDetail.children_order_no}" class="am-btn am-btn-default am-btn-xs am-text-success btn_complete" style="<c:if test='${OrderTradeDetail.is_complete == 0}'>display: none;</c:if>">完成</button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="11" align="center"><label style="color: #ac2925">您现在还没有任何订单</label></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>
