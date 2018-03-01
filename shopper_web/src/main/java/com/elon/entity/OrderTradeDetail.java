package com.elon.entity;

import java.util.Date;

/**
 * 2017/11/21 14:13.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 订单明细表
 */
public class OrderTradeDetail {

    private Integer id;

    /**
     * 子订单号
     */
    private String children_order_no;

    /**
     * 父订单号
     */
    private String parent_order_no;

    private Integer food_id;

    private String single_prince;

    private Integer bug_num;

    private String sum_money;

    /**
     * 订单是否完成 0未完成 1已完成
     */
    private Integer is_complete;

    private String shopper_nick_name;

    /**
     * 父级菜单id
     */
    private String parent_food_id;

    private Date add_time;


    //链接查询其他的表 的字段
    private String food_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChildren_order_no() {
        return children_order_no;
    }

    public void setChildren_order_no(String children_order_no) {
        this.children_order_no = children_order_no;
    }

    public String getParent_order_no() {
        return parent_order_no;
    }

    public void setParent_order_no(String parent_order_no) {
        this.parent_order_no = parent_order_no;
    }

    public Integer getFood_id() {
        return food_id;
    }

    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public String getSingle_prince() {
        return single_prince;
    }

    public void setSingle_prince(String single_prince) {
        this.single_prince = single_prince;
    }

    public Integer getBug_num() {
        return bug_num;
    }

    public void setBug_num(Integer bug_num) {
        this.bug_num = bug_num;
    }

    public String getSum_money() {
        return sum_money;
    }

    public void setSum_money(String sum_money) {
        this.sum_money = sum_money;
    }

    public Integer getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(Integer is_complete) {
        this.is_complete = is_complete;
    }

    public String getShopper_nick_name() {
        return shopper_nick_name;
    }

    public void setShopper_nick_name(String shopper_nick_name) {
        this.shopper_nick_name = shopper_nick_name;
    }

    public String getParent_food_id() {
        return parent_food_id;
    }

    public void setParent_food_id(String parent_food_id) {
        this.parent_food_id = parent_food_id;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
}
