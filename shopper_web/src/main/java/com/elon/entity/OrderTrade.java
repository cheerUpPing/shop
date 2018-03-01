package com.elon.entity;

import java.util.Date;

/**
 * 2017/11/21 13:01.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class OrderTrade {

    private Integer id;

    /**
     * 订单号
     */
    private String order_no;

    /**
     * 微信订单号
     */
    private String wx_order_no;

    /**
     * 桌子编号
     */
    private Integer seat_no;

    /**
     * 订单总价
     */
    private String sum_money;

    /**
     * 是否打包 0打包 1不打包
     */
    private Integer is_packed;

    /**
     * 支付状态 0未支付 1已支付 2支付失败
     */
    private Integer pay_status;

    /**
     * 订单是否完成 0未完成 1已完成
     */
    private Integer is_complete;

    /**
     * 商户名
     */
    private String shopper_nick_name;

    /**
     * 订单提交时间
     */
    private Date order_time;

    /**
     * 订单支付时间
     */
    private Date pay_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getWx_order_no() {
        return wx_order_no;
    }

    public void setWx_order_no(String wx_order_no) {
        this.wx_order_no = wx_order_no;
    }

    public String getShopper_nick_name() {
        return shopper_nick_name;
    }

    public void setShopper_nick_name(String shopper_nick_name) {
        this.shopper_nick_name = shopper_nick_name;
    }

    public Integer getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(Integer seat_no) {
        this.seat_no = seat_no;
    }

    public String getSum_money() {
        return sum_money;
    }

    public void setSum_money(String sum_money) {
        this.sum_money = sum_money;
    }

    public Integer getIs_packed() {
        return is_packed;
    }

    public void setIs_packed(Integer is_packed) {
        this.is_packed = is_packed;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public Integer getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(Integer is_complete) {
        this.is_complete = is_complete;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }
}
