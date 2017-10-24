package com.zhanglifan.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/24 22:25
 * Description:
 */
public class DateConvert implements Converter<String,Date> {
    public Date convert(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
