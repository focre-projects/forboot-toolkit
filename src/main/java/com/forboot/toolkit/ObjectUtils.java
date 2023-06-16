package com.forboot.toolkit;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: ObjectUtils
 * @Description: 对象工具类
 * @Author: ye21st
 * @Date: 2023/6/16 09:36
 */
public class ObjectUtils {

    /**
     * 判断object是否为空,集合会校验size
     */
    public static boolean isNull(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断object是否不为空,集合会校验size
     */
    public static boolean isNotNull(Object... obj) {
        return !isNull(obj);
    }

    /**
     * 对象非空判断
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 对象空判断
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Iterable) {
            return !((Iterable<?>) obj).iterator().hasNext();
        }
        if (obj instanceof Iterator) {
            return !((Iterator<?>) obj).hasNext();
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        // else
        return false;
    }

    /**
     * 对象转为字节数组
     *
     * @param obj 待转字节数组对象
     * @return 字节数组
     * @throws IOException
     */
    public static byte[] toByteArray(Object obj) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        return outputStream.toByteArray();
    }

    /**
     * 字节数组转化为泛型T对象
     *
     * @param byteArray 字节数组
     * @param <T>       泛型T对象
     * @throws IOException
     * @throws ClassNotFoundException
     * @return
     */
    public static <T> T readObject(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream oInputStream = new ObjectInputStream(inputStream);
        return (T) oInputStream.readObject();
    }

}
