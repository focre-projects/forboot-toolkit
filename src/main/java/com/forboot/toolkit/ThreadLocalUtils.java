package com.forboot.toolkit;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ThreadLocalUtils
 * @Description: TODO
 * @Author: ye21st
 * @Date: 2023/6/15 18:14
 */
public class ThreadLocalUtils {

    private static final ThreadLocal<Map<String, Object>> REQUEST_DATA = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        Map<String, Object> dataMap = REQUEST_DATA.get();
        dataMap.put(key, value);
    }

    public static void set(Map<String, Object> dataMap) {
        REQUEST_DATA.set(dataMap);
    }

    public static <T> T get(String key) {
        Map<String, Object> dataMap = REQUEST_DATA.get();
        if (dataMap != null) {
            Object value = dataMap.get(key);
            if (value != null) {
                try {
                    return (T) value;
                } catch (ClassCastException e) {
                    // 处理类型转换异常
                    throw new ClassCastException("Unable to cast value to the specified type");
                }
            }
        }
        return null;
    }

    public static <T> T get(String key, Class<T> type) {
        Map<String, Object> dataMap = REQUEST_DATA.get();
        if (dataMap != null) {
            Object value = dataMap.get(key);
            if (value != null) {
                try {
                    return type.cast(value);
                } catch (ClassCastException e) {
                    // 处理类型转换异常
                    throw new ClassCastException("Unable to cast value to the specified type");
                }
            }
        }
        return null;
    }

    public static Map<String, Object> getAll() {
        return new HashMap<>(REQUEST_DATA.get());
    }

    public static void remove(String key) {
        Map<String, Object> dataMap = REQUEST_DATA.get();
        if (dataMap != null) {
            dataMap.remove(key);
        }
    }

    public static void removeAll() {
        REQUEST_DATA.remove();
    }

}
