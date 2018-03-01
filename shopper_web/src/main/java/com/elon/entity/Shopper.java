package com.elon.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/11/21 10:18.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class Shopper {

    private Integer id;

    private String nick_name;

    private String pwd;

    private int telphone;

    /**
     * 店主姓名
     */
    private String really_name;

    private String shop_name;

    /**
     * 主营业务
     */
    private String major_work;

    private Date last_login;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getTelphone() {
        return telphone;
    }

    public void setTelphone(int telphone) {
        this.telphone = telphone;
    }

    public String getReally_name() {
        return really_name;
    }

    public void setReally_name(String really_name) {
        this.really_name = really_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getMajor_work() {
        return major_work;
    }

    public void setMajor_work(String major_work) {
        this.major_work = major_work;
    }

    public String getLast_login() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return last_login == null ? "" : sdf.format(last_login);
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shopper) {
            return this.nick_name.hashCode() == ((Shopper) obj).nick_name.hashCode();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.nick_name.hashCode();
    }
}
