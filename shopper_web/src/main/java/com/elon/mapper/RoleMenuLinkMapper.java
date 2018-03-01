package com.elon.mapper;

import com.elon.entity.RoleMenuLink;

import java.util.List;

/**
 * 2017/11/21 10:11.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface RoleMenuLinkMapper extends BaseMapper<RoleMenuLink> {

    List<RoleMenuLink> selectAll();

    RoleMenuLink selectByRoleId(Integer roleId);
}
