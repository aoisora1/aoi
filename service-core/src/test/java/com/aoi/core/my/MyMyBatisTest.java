package com.aoi.core.my;

import com.aoi.IntegrationTest;
import com.aoi.core.my.mybatis.AppService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MyMyBatisTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 13:30
 */
@IntegrationTest
public class MyMyBatisTest {
    @Autowired
    AppService appService;

    @Test
    public void test() {
        appService.print(appService.crete(Lists.newArrayList("idTest", "nameTest")));
    }
}
