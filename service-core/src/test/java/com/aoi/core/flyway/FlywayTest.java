package com.aoi.core.flyway;

import com.aoi.core.util.FileUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public void testFlywaySqlFile() throws IOException {
        String path = FileUtil.getResourcePath("");
        String filePathString = path.concat(File.separator).concat("db").concat(File.separator).concat("migration");
        Path filePath = new File(filePathString).toPath();

        Set<String> unNormalFiles = new HashSet<>();
        Set<String> versions = new HashSet<>();
        Files.walk(filePath).filter(p -> {
            return p.toFile().isFile();
        }).forEach(f -> {
            String name = f.getFileName().toString();
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
        });
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
