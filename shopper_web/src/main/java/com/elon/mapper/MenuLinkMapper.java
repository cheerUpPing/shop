package com.elon.mapper;

import com.elon.entity.MenuLink;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface MenuLinkMapper extends BaseMapper<MenuLink> {

    List<MenuLink> selectAll();

    List<MenuLink> selectByParentIds(String[] array);

    MenuLink selectByMenuUrl(String menuUrl);
}
