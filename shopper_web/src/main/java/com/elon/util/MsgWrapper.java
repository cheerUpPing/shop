package com.elon.util;

/**
 * 2017/11/21 14:24.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * ajax消息包裹
 */
public class MsgWrapper {

    private String code = "0000";

    private String msg = "request success!";

    private Object data = null;

    public MsgWrapper() {
    }

    public MsgWrapper(Object data) {
        this.data = data;
    }

    public MsgWrapper(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
