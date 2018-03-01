package com.elon.entity;

import java.util.Date;

/**
 * 用户角色关系表
 */
public class ShopperRole {

    private Integer id;

    private Integer role_id;

    private String shopper_nick_name;

    private Date add_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getShopper_nick_name() {
        return shopper_nick_name;
    }

    public void setShopper_nick_name(String shopper_nick_name) {
        this.shopper_nick_name = shopper_nick_name;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
