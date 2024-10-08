package com.aoi.core.flyway;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName FlywayTest
 * @Description 脚本规范DT
 * @Author 86184
 * @Date 2024/10/7 16:51
 */
public class FlywayTest {

    // flyway脚本版本号不可重复
    // 脚本严格按照V|Rxxx__{}.sql格式
    @Test
    public void testFlywaySqlFile() {
        File directory = FileUtils.getFile(String.join(File.separator, "src", "main", "resources", "db", "migration"));
        File[] files = directory.listFiles();
        Set<String> unNormalFiles = new HashSet<>();
        Set<String> versions = new HashSet<>();
        for (File file : files) {
            String name = file.getName();
            if (!checkFlywayName(name)) {
                unNormalFiles.add(name);
            } else {
                String version = name.split("__")[0];
                if (versions.contains(version)) {
                    throw new RuntimeException(version + " is duplicate");
                } else {
                    versions.add(version);
                }
            }
        }

        System.out.println(unNormalFiles);
        Assertions.assertThat(unNormalFiles).isEmpty();
    }

    private boolean checkFlywayName(String name) {
        if (!name.startsWith("V") && !name.startsWith("R")) {
            return false;
        }
        if (!name.endsWith(".sql")) {
            return false;
        }
        String[] split = name.split("__");
        if (split.length != 2) {
            return false;
        }
        return true;
    }
}
