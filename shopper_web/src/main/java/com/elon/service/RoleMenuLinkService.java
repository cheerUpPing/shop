package com.elon.service;

import com.elon.entity.RoleMenuLink;
import com.elon.mapper.RoleMenuLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2017/11/21 10:48.
 * <p>
 * Email: cheerUpPing@163.com
 */
@Service
public class RoleMenuLinkService extends BaseService<RoleMenuLink> {

    @Autowired
    private RoleMenuLinkMapper roleMenuLinkMapper;

    @Override
    public void insert(RoleMenuLink roleMenuLink) {
        roleMenuLinkMapper.insert(roleMenuLink);
    }

    /**
     * 通过角色id获取 角色和菜单关系记录
     *
     * @param roleId
     * @return
     */
    public RoleMenuLink selectByRoleId(Integer roleId) {
        return roleMenuLinkMapper.selectByRoleId(roleId);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(RoleMenuLink roleMenuLink) {

    }

    @Override
    public RoleMenuLink selectById(Integer id) {
        return null;
    }
}
