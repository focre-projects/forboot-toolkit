package com.forboot.toolkit;

import org.springframework.util.CollectionUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: IoUtils
 * @Description: IO 工具类
 * @Author: ye21st
 * @Date: 2023/6/16
 */
public class IoUtils {

    /**
     * 输入流写入输出流
     *
     * @param is {@link InputStream}
     * @param os {@link OutputStream}
     * @throws IOException
     */
    public static void write(InputStream is, OutputStream os) throws IOException {
        if (null != is && null != os) {
            byte[] buffer = new byte[2048];
            int length;
            // 使用 try-with-resources 语句自动关闭流
            try (is; os) {
                while (-1 != (length = is.read(buffer))) {
                    os.write(buffer, 0, length);
                }
            }
        }
    }

    /**
     * 关闭流对象
     *
     * @param cls 可关闭的流对象列表
     * @return IOException
     */
    public static IOException close(Closeable... cls) {
        // 使用 CollectionUtils.isEmpty 方法判断数组是否为空
        if (CollectionUtils.isEmpty(List.of(cls))) {
            return null;
        }
        IOException exception = null;
        for (Closeable closeable : cls) {
            exception = close(closeable);
            if (null != exception) {
                break;
            }
        }
        return exception;
    }

    /**
     * 关闭流对象
     *
     * @param closeableColl 可关闭的流对象列表
     * @return IOException
     */
    public static IOException close(Collection<? extends Closeable> closeableColl) {
        if (CollectionUtils.isEmpty(closeableColl)) {
            return null;
        }
        IOException exception = null;
        for (Closeable closeable : closeableColl) {
            exception = close(closeable);
            if (null != exception) {
                break;
            }
        }
        return exception;
    }

    /**
     * 关闭流对象
     *
     * @param closeable 可关闭的流对象
     * @return IOException
     */
    public static IOException close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                return e;
            }
        }
        return null;
    }

}
