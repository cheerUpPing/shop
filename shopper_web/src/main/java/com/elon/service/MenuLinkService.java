package com.elon.service;

import com.elon.entity.MenuLink;
import com.elon.mapper.MenuLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class MenuLinkService extends BaseService<MenuLink> {

    @Autowired
    private MenuLinkMapper menuLinkMapper;

    @Override
    public void insert(MenuLink shopper) {
        menuLinkMapper.insert(shopper);
    }

    public List<MenuLink> selectAll() {
        return menuLinkMapper.selectAll();
    }

    /**
     * 通过大类菜单id，查询大类菜单和对应的小类菜单
     *
     * @param array
     * @return
     */
    public List<MenuLink> selectByParentIds(String[] array) {
        return menuLinkMapper.selectByParentIds(array);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(MenuLink menuLink) {

    }

    @Override
    public MenuLink selectById(Integer id) {
        return null;
    }

    /**
     * 通过菜单名字获取菜单
     *
     * @param menuUrl
     * @return
     */
    public MenuLink selectByMenuUrl(String menuUrl){
        return menuLinkMapper.selectByMenuUrl(menuUrl);
    }
}
