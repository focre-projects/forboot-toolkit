package com.forboot.toolkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 日期工具类
 * @ClassName: DateUtils
 * @Author: ye21st
 * @Date: 2023/6/15
 * @Copyright: 尊重知识产权，CV 请保留版权 <a href="https://www.forboot.com">ForBoot</a> 出品
 */
public class DateUtils {

    public interface Pattern {
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
        String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
        String YYYY_MM_DD = "yyyy-MM-dd";
    }

    public static String getNowTime() {
        return nowFormat(Pattern.YYYY_MM_DD_HH_MM_SS);
    }

    public static String nowFormat(String pattern) {
        return LocalDateTime.now().format(ofPattern(pattern));
    }

    public static DateTimeFormatter ofPattern(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

}
