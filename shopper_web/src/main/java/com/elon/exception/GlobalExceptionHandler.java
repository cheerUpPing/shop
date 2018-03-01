package com.elon.exception;

import com.elon.contants.Contants;
import com.elon.util.LogUtil;
import com.elon.util.MsgWrapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import static com.elon.contants.Contants.Error.unknow_exception;

/**
 * 2017/11/21 15:06.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 在执行requestMapping报错(参数赋值错误,执行逻辑错误)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public MsgWrapper exceptionHandler(Throwable e, HttpServletResponse response) {
        logger.error(LogUtil.getStackTrace(e));
        MsgWrapper msgWrapper = null;
        String msg = e.getMessage();
        if (e instanceof ShopException) {
            String[] msgs = msg.split("#", -1);
            msgWrapper = new MsgWrapper(msgs[0], msgs[1]);
        } else {
            msgWrapper = new MsgWrapper(unknow_exception, Contants.Error.getErrorMsg(unknow_exception));
        }
        return msgWrapper;
    }
}
