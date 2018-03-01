<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商户后台</title>
    <meta name="keywords" content="index">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${path}/template/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${path}/template/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <script src="${path}/template/js/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/js/message.js"></script>
    <link rel="stylesheet" href="${path}/template/css/amazeui.css"/>
    <link rel="stylesheet" href="${path}/template/css/admin.css">
    <script src="${path}/template/js/app.js"></script>
    <script src="${path}/js/common.js"></script>
    <script type="text/javascript">
        var menu_link_divs_ = "${menuLinkDivs}" + "div_menu_00";
        var menu_link_lis_ = "${menuLinkLis}" + "li_menu_00";
        var menuLinkBtns_ = "${menuLinkBtns}" + "btn_00";
        var menu_link_divs = menu_link_divs_.split(",");
        var menu_link_lis = menu_link_lis_.split(",");
        var menu_link_btns = menuLinkBtns_.split(",");
        //其他div隐藏,notHidedDiv不被隐藏的div的id
        function hideAndShowDiv(toShowDiv) {
            for (var i = 0; i < menu_link_divs.length; i++) {
                if (menu_link_divs[i] != "div_menu_00"){
                    $("#" + menu_link_divs[i]).hide();
                    $("#" + menu_link_divs[i]).empty();
                }else {
                    $("#" + menu_link_divs[i]).hide();
                }
            }
            $("#" +　toShowDiv).fadeIn();
        }
        //显示导航栏并且增加颜色,其他的导航栏就清楚颜色,menuId待显示和增加颜色的的导航栏对应的id,其余的都清楚颜色
        function showAndColoredLi(menuId) {
            var btn = "btn_" + menuId;
            //删除其他导航栏的颜色
            for (var k=0;k<menu_link_btns.length;k++){
                if (menu_link_btns[k] != btn){
                    $("#" +　menu_link_btns[k]).css("color","");
                    $("#" +　menu_link_btns[k]).css("background-color","");
                    $("#" +　menu_link_btns[k]).css("border-color","");
                }
            }
            var toShowLi = "#li_menu_" + menuId;
            $(toShowLi).show();
            $("#" +　btn).css("color","#fff");
            $("#" +　btn).css("background-color","#ff6c60");
            $("#" +　btn).css("border-color","#ff6c60");
        }
        $(function () {
            //用来区分单击/双击事件
            var timer = null;
            //导航栏标签双击关闭事件
            $(".daohang #daohang_ul li button").bind("dblclick",function () {
                if (menu_id != "00"){
                    clearTimeout(timer);
                    var menu_id = $(this).attr("menu_id");
                    var menu_link_li = "li_menu_" + menu_id;
                    var menu_link_div = "div_menu_" + menu_id;
                    $("#" + menu_link_div).hide();
                    $("#" + menu_link_div).empty();
                    $("#" + menu_link_li).hide();
                }
            });
            //导航标签--点击事件
            $(".daohang #daohang_ul li button").bind("click",function () {
                var menu_id = $(this).attr("menu_id");
                if (menu_id != "00"){
                    clearTimeout(timer);
                    timer = setTimeout(function() { //在单击事件中添加一个setTimeout()函数，设置单击事件触发的时间间隔
                        console.info("1ji");
                    }, 300);
                    //触发左侧菜单的点击事件
                    $("#li_menu_container a[menu_id='"+menu_id+"']").click();
                }else {//首页单击事件
                    showAndColoredLi(menu_id);
                    hideAndShowDiv("div_menu_" + menu_id);
                }
            });
            //给每个菜单链接绑定点击事件
            $("#li_menu_container a").bind("click",function (e) {
                e.preventDefault();
                var menu_link = $(this).attr("menu_link");
                var menu_name = $(this).attr("menu_name");
                var menu_id = $(this).attr("menu_id");
                var selectMenuLink = "div_menu_" + menu_id;
                //增加div的内容并且显示
                var data = sendRequestPage("POST", false, menu_link, {menuId:menu_id}, "${path}/loginIndex.do");
                hideAndShowDiv(selectMenuLink);
                $("#" + selectMenuLink).append(data);
                //导航显示
                showAndColoredLi(menu_id);
            });
        });
    </script>
</head>
<!--[if lte IE 9]><p class="browsehappy">升级你的浏览器吧！ <a href="http://se.360.cn/" target="_blank">升级浏览器</a>以获得更好的体验！</p>
<![endif]-->
<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand"><img src="${path}/template/i/logo.png"></div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">
            <li>
                <div style="padding-top: 13px;">
                    <span>${sessionScope.shopper_nick_name.nick_name}</span>
                    <span>退出</span>
                </div>
            </li>

            <li class="am-hide-sm-only" style="float: right;"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>
<c:choose>
<c:when test="${empty menuLinkListMap}">
    <div align="center" style="width: 100%">
        <h1 class="admin-header">请联系管理员给您分配菜单权限</h1>
    </div>
</c:when>
<c:otherwise>
<div class="am-cf admin-main">
    <div class="nav-navicon admin-main admin-sidebar">
        <div class="sideMenu">

            <c:forEach items="${menuLinkListMap}" var="entry" varStatus="status">
                <h3 class="${entry.key.cls}"><em></em>&nbsp;&nbsp;<a href="#"
                                                                     style="font-size: 18px">${entry.key.menu_name}</a>
                </h3>
                <ul>
                    <c:forEach items="${entry.value}" var="menuLink">
                        <li style="margin-left: 14px;" id="li_menu_container"><a href="#"
                                                                                 menu_link="${path}/${menuLink.menu_url}"
                                                                                 menu_name="${menuLink.menu_name}"
                                                                                 menu_id="${menuLink.id}"
                                                                                 style="font-size: 12px">${menuLink.menu_name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
        <!-- sideMenu End -->
        <script type="text/javascript">
            jQuery(".sideMenu").slide({
                titCell: "h3", //鼠标触发对象
                targetCell: "ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
                effect: "slideDown", //targetCell下拉效果
                delayTime: 500, //效果时间
                triggerTime: 150, //鼠标延迟触发时间（默认150）
                defaultPlay: true,//默认是否执行效果（默认true）
                returnDefault: true //鼠标从.sideMen移走后返回默认状态（默认false）
            });
        </script>
    </div>
    <!--主体内容-->
    <div class="admin-content">

        <div class="daohang">
            <ul id="daohang_ul">
                <li id="li_menu_00">
                    <button type="button" class="am-btn am-btn-default am-radius am-btn-xs" id="btn_00" menu_id="00" style="color: #fff;background-color: #ff6c60;border-color: #ff6c60;"> 首页</button>
                </li>
                <c:forEach items="${menuLinkListMap}" var="entry">
                    <c:forEach items="${entry.value}" var="menuLink">
                        <li style="display: none" id="li_menu_${menuLink.id}">
                            <button type="button" id="btn_${menuLink.id}" menu_id="${menuLink.id}"
                                    class="am-btn am-btn-default am-radius am-btn-xs">${menuLink.menu_name}
                            </button>
                        </li>
                    </c:forEach>
                </c:forEach>
            </ul>
        </div>

        <div class="admin" id="main_container">
            <!--默认内容-->
            <div id="div_menu_00">
                我是默认内容
            </div>
            <!--下面是多个div,保存每个链接的内容-->
            <c:forEach items="${menuLinkListMap}" var="entry">
                <c:forEach items="${entry.value}" var="menuLink">
                    <div id="div_menu_${menuLink.id}" style="display: none"></div>
                </c:forEach>
            </c:forEach>


            <div class="foods">
                <ul>版权所有--深圳市心欣科技有限公司,联系电话:徐先生,13088839347</ul>
                <dl><a href="#" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a></dl>
            </div>
        </div>
    </div>
    </c:otherwise>
    </c:choose>
    <!--[if lt IE 9]>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="${path}/template/js/polyfill/rem.min.js"></script>
    <script src="${path}/template/js/polyfill/respond.min.js"></script>
    <script src="${path}/template/js/amazeui.legacy.js"></script>
    <![endif]-->

    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="${path}/template/js/amazeui.js"></script>
    <!--<![endif]-->


</body>
</html>