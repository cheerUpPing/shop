package com.elon.util;


import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * string--date转换类
 * 用于请求参数转换
 */
public class DateConvert implements Converter<String, Date> {

    private Logger logger = Logger.getLogger(DateConvert.class);

    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            logger.error("日期转换出错:" + LogUtil.getStackTrace(e) + " 时间:" + simpleDateFormat.format(new Date()));
            e.printStackTrace();
        }
        return date;
    }
}
