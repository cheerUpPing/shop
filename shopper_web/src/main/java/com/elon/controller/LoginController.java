package com.elon.controller;

import com.alibaba.fastjson.JSON;
import com.elon.contants.Contants;
import com.elon.entity.MenuLink;
import com.elon.entity.RoleMenuLink;
import com.elon.entity.Shopper;
import com.elon.entity.ShopperRole;
import com.elon.service.MenuLinkService;
import com.elon.service.RoleMenuLinkService;
import com.elon.service.ShopperRoleService;
import com.elon.service.ShopperService;
import com.elon.util.MsgWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    private ShopperService shopperService;
    @Autowired
    private ShopperRoleService shopperRoleService;
    @Autowired
    private RoleMenuLinkService roleMenuLinkService;
    @Autowired
    private MenuLinkService menuLinkService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/loginIndex.do")
    public String loginIndex() {
        return "login";
    }

    /**
     * 网站主页面
     *
     * @return
     */
    @RequestMapping("/mainIndex.do")
    public String mainIndex(HttpServletRequest request) {
        //获取用户菜单
        HttpSession session = request.getSession();
        Shopper shopper = (Shopper) session.getAttribute(Contants.curr_user);
        ShopperRole shopperRole = shopperRoleService.selectByShopperNickName(shopper.getNick_name());
        List<MenuLink> menuLinks = null;
        if (shopperRole != null) {
            RoleMenuLink roleMenuLink = roleMenuLinkService.selectByRoleId(shopperRole.getRole_id());
            if (roleMenuLink != null) {
                //大类和小类的菜单都包含
                String[] parentIds = roleMenuLink.getMenu_link_id().split(",");
                menuLinks = menuLinkService.selectByParentIds(parentIds);
            }
        }
        Map<MenuLink, List<MenuLink>> menuLinkListMap = null;
        if (menuLinks != null) {
            menuLinkListMap = sortMenuLink(menuLinks);
            System.out.println(JSON.toJSONString(menuLinkListMap));
            //对应前端页面 导航菜单li/内容包裹div/导航栏颜色
            StringBuilder menuLinkDivsStr = new StringBuilder();
            StringBuilder menuLinkLiStr = new StringBuilder();
            StringBuilder menuLinkBtnStr = new StringBuilder();
            for (Map.Entry<MenuLink, List<MenuLink>> entry : menuLinkListMap.entrySet()) {
                List<MenuLink> menuLinkList = entry.getValue();
                for (MenuLink menuLink : menuLinkList) {
                    menuLinkDivsStr.append("div_menu_").append(menuLink.getId()).append(",");
                    menuLinkLiStr.append("li_menu_").append(menuLink.getId()).append(",");
                    menuLinkBtnStr.append("btn_").append(menuLink.getId()).append(",");
                }
            }
            request.setAttribute("menuLinkListMap", menuLinkListMap);
            request.setAttribute("menuLinkDivs", menuLinkDivsStr.toString());
            request.setAttribute("menuLinkLis", menuLinkLiStr.toString());
            request.setAttribute("menuLinkBtns", menuLinkBtnStr.toString());
        }
        return "main";
    }

    /**
     * 正式登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public MsgWrapper login(String username, String password, HttpServletRequest request) {
        MsgWrapper msgWrapper = null;
        Shopper shopper = shopperService.selectByNickName(username);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            msgWrapper = new MsgWrapper(Contants.Error.param_not_empty, Contants.Error.getErrorMsg(Contants.Error.param_not_empty));
        } else {
            if (shopper == null || !shopper.getPwd().equals(password)) {
                msgWrapper = new MsgWrapper(Contants.Error.user_not_exist, Contants.Error.getErrorMsg(Contants.Error.user_not_exist));
            } else {
                msgWrapper = new MsgWrapper();
                HttpSession session = request.getSession();
                session.setAttribute(Contants.curr_user, shopper);
            }
        }
        return msgWrapper;
    }

    /**
     * 菜单分类别
     *
     * @param menuLinkList
     * @return
     */
    private Map<MenuLink, List<MenuLink>> sortMenuLink(List<MenuLink> menuLinkList) {
        Map<MenuLink, List<MenuLink>> menuLinkListMap = new TreeMap<MenuLink, List<MenuLink>>(new Comparator<MenuLink>() {
            @Override
            public int compare(MenuLink o1, MenuLink o2) {
                return o1.getOrder_no() - o2.getOrder_no();
            }
        });
        //获取大类菜单
        for (int i = 0; i < menuLinkList.size(); i++) {
            MenuLink menuLink = menuLinkList.get(i);
            if (menuLink.getIs_first_menu() == 0) {
                List<MenuLink> menuLinks = new ArrayList<MenuLink>();
                menuLink.setCls(Contants.classStyles.get(i));
                menuLinkListMap.put(menuLink, menuLinks);
            }
        }
        //获取小类菜单
        for (Map.Entry<MenuLink, List<MenuLink>> entry : menuLinkListMap.entrySet()) {
            MenuLink key = entry.getKey();
            List<MenuLink> val = entry.getValue();
            for (MenuLink menuLink : menuLinkList) {
                if (key.getId().equals(menuLink.getParent_menu_id())) {
                    val.add(menuLink);
                }
            }
        }
        //小类菜单排序
        for (Map.Entry<MenuLink, List<MenuLink>> entry : menuLinkListMap.entrySet()) {
            List<MenuLink> val = entry.getValue();
            Collections.sort(val, new Comparator<MenuLink>() {
                @Override
                public int compare(MenuLink o1, MenuLink o2) {
                    return o1.getOrder_no() - o2.getOrder_no();
                }
            });
        }
        return menuLinkListMap;
    }

}
