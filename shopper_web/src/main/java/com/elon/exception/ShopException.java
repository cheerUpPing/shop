package com.elon.exception;

import com.elon.contants.Contants;

/**
 * 2017/11/21 13:48.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 商户支付异常
 */
public class ShopException extends RuntimeException {

    public ShopException(String errorKey) {
        this(errorKey, Contants.Error.getErrorMsg(errorKey));
    }

    public ShopException(String errorKey, String errorMsg) {
        super(errorKey + "#" + errorMsg);
    }
}
