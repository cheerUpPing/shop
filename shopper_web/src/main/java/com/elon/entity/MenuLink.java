package com.elon.entity;

import java.util.Date;

/**
 * 菜单
 */
public class MenuLink {

    private Integer id;

    private String menu_name;

    private String menu_url;

    private Integer order_no;

    private Integer is_first_menu;

    private Integer parent_menu_id;

    private Date add_time;

    //不是数据库字段
    private String cls;//css对应的class


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public Integer getIs_first_menu() {
        return is_first_menu;
    }

    public void setIs_first_menu(Integer is_first_menu) {
        this.is_first_menu = is_first_menu;
    }

    public Integer getParent_menu_id() {
        return parent_menu_id;
    }

    public void setParent_menu_id(Integer parent_menu_id) {
        this.parent_menu_id = parent_menu_id;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}
