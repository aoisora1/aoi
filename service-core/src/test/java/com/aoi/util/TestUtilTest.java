package com.aoi.util;

import com.aoi.core.util.TestUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @ClassName TestUtilTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/8 21:21
 */
public class TestUtilTest {
    private static MockedStatic<TestUtil> testUtilMockedStatic;

    @BeforeAll
    public static void init() {
        testUtilMockedStatic = Mockito.mockStatic(TestUtil.class);
    }

    @AfterAll
    public static void close() {
        testUtilMockedStatic.close();
    }

    @Test
    public void test() {
        testUtilMockedStatic.when(TestUtil::test).thenReturn("test2");
        Assertions.assertEquals("test2", TestUtil.test());
    }
}
