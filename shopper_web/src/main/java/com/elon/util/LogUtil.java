package com.elon.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 2017/4/20 9:53.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 把错误信息打印到日志文件
 */
public class LogUtil {

    /**
     * 获取错误堆栈信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
