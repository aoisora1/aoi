package com.aoi.core.controller;

import com.aoi.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName TestControllerTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/8 20:59
 */
@IntegrationTest
public class TestControllerIntegrationTest {
    @Autowired
    private TestController testController;

    @Test
    public void test() {
        Assertions.assertEquals(testController.get("", ""), "test");
    }
}
