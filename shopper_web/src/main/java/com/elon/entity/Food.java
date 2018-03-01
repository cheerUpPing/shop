package com.elon.entity;

import java.util.Date;

/**
 * 2017/11/22 10:32.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 商品菜单
 */
public class Food {


    private Integer id;

    private String food_name;

    private String food_desc;

    private String food_price;

    private Integer food_order;

    private Integer food_sale;

    private String food_image_name;

    private Integer parent_food_id;

    private Integer is_parent;

    private String shopper_nick_name;


    private Date add_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public Integer getFood_sale() {
        return food_sale;
    }

    public void setFood_sale(Integer food_sale) {
        this.food_sale = food_sale;
    }

    public Integer getFood_order() {
        return food_order;
    }

    public void setFood_order(Integer food_order) {
        this.food_order = food_order;
    }

    public String getFood_image_name() {
        return food_image_name;
    }

    public void setFood_image_name(String food_image_name) {
        this.food_image_name = food_image_name;
    }

    public Integer getParent_food_id() {
        return parent_food_id;
    }

    public void setParent_food_id(Integer parent_food_id) {
        this.parent_food_id = parent_food_id;
    }

    public Integer getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(Integer is_parent) {
        this.is_parent = is_parent;
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
