package com.elon.entity;

import java.util.Date;

/**
 * 角色和资源关系表
 */
public class RoleMenuLink {

    private Integer id;

    private Integer role_id;

    private String menu_link_id;

    private Date add_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenu_link_id() {
        return menu_link_id;
    }

    public void setMenu_link_id(String menu_link_id) {
        this.menu_link_id = menu_link_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
