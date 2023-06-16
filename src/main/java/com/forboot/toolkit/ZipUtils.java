package com.forboot.toolkit;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: ZipUtils
 * @Description: Zip 操作工具类
 * @Author: ye21st
 * @Date: 2023/6/16 09:38
 */
public class ZipUtils {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    @Getter
    @Setter
    @Builder
    public static class FileEntry {
        private InputStream inputStream;
        private ZipEntry zipEntry;

    }

    public static void zipFile(HttpServletResponse response, List<FileEntry> fes)
            throws IOException {
        if (!StringUtils.hasLength(response.getHeader(CONTENT_DISPOSITION))) {
            String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHmmss"));
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + filename + ".zip");
        }
        zipFile(response.getOutputStream(), fes);
    }

    public static void zipFile(OutputStream os, List<FileEntry> fileEntries) throws IOException {
        ZipOutputStream zos = null;
        try {
            final Map<String, Integer> nameMap = new HashMap<>();
            zos = new ZipOutputStream(os);
            for (FileEntry fileEntry : fileEntries) {
                ZipEntry zipEntry = fileEntry.getZipEntry();
                Integer index = nameMap.get(zipEntry.getName());
                if (null != index) {
                    // 文件名重复处理
                    ++index;
                } else {
                    index = 0;
                }
                nameMap.put(zipEntry.getName(), index);
                if (index > 0) {
                    // 修改文件名
                    ZipEntry ze = new ZipEntry(index + zipEntry.getName());
                    ze.setComment(zipEntry.getComment());
                    fileEntry.setZipEntry(ze);
                }
                fileStreamZip(zos, fileEntry);
            }
        } finally {
            if (null != zos) {
                zos.close();
            }
        }
    }

    /**
     * 压缩文件流
     *
     * @param zos       ZIP 压缩文件输出流
     * @param fileEntry 压缩文件条目
     * @throws IOException IO错误时抛出
     */
    private static void fileStreamZip(ZipOutputStream zos, FileEntry fileEntry) throws IOException {
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            zos.putNextEntry(fileEntry.getZipEntry());
            byte[] buffer = new byte[1024 * 10];
            int read;
            int size = buffer.length;
            is = fileEntry.getInputStream();
            bis = new BufferedInputStream(is, size);
            while ((read = bis.read(buffer, 0, size)) != -1) {
                zos.write(buffer, 0, read);
            }
        } finally {
            zos.closeEntry();
            if (null != is) {
                is.close();
            }
            if (null != bis) {
                bis.close();
            }
        }
    }

}
