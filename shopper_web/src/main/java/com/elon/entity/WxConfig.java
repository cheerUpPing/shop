package com.elon.entity;

import java.util.Date;

/**
 * 2017/11/24 15:38.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 微信公众号设置
 */
public class WxConfig {

    private Integer id;

    private String shopper_nick_name;

    //微信公账号appid
    private String app_id = null;

    //微信商户id
    private String mch_id = null;

    //微信支付key
    private String mch_key = null;

    //信息录入时间
    private Date add_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopper_nick_name() {
        return shopper_nick_name;
    }

    public void setShopper_nick_name(String shopper_nick_name) {
        this.shopper_nick_name = shopper_nick_name;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getMch_key() {
        return mch_key;
    }

    public void setMch_key(String mch_key) {
        this.mch_key = mch_key;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
