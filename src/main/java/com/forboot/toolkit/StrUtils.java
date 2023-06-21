package com.forboot.toolkit;

/**
 * @Description: 字符串工具类
 * @ClassName: StrUtils
 * @Author: ye21st
 * @Date: 2023/6/15
 * @Copyright: 尊重知识产权，CV 请保留版权 <a href="https://www.forboot.com">ForBoot</a> 出品
 */
public class StrUtils {

    /**
     * 判断字符串是否为空，包括null和空白字符
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空，包括null和空白字符
     * @param str 字符串
     * @return 是否不为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
