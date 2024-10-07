package com.aoi.core.util;

/**
 * @ClassName FileUtil
 * @Description FileUtil
 * @Author 86184
 * @Date 2024/10/7 17:14
 */
public class FileUtil {

    public static String getResourcePath(String fileName) {
        return FileUtil.class.getClassLoader().getResource(fileName).getFile();
    }
}
