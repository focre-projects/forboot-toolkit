package com.forboot.toolkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: FileUtils
 * @Description: 文件工具类
 * @Author: ye21st
 * @Date: 2023/6/19 11:10
 */
public class FileUtils {

    /**
     * 获取文件的 MINI_TYPE
     * @param file 文件
     * @return MINI_TYPE 类型
     * @throws IOException IO 异常
     */
    public static String getMimeType(File file) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        return Files.probeContentType(path);
    }

    /**
     * 获取文件的扩展名
     * @param mimeType MINI_TYPE 类型
     * @return 扩展名
     */
    public static String getExtension(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return null;
        }
        String[] parts = mimeType.split("/");
        if (parts.length != 2) {
            return null;
        }
        return parts[1];
    }

    /**
     * 判断是否是图片
     * @param mimeType MINI_TYPE 类型
     * @return 是否是图片
     */
    public static boolean isImage(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return false;
        }
        return mimeType.startsWith("image/");
    }

}
