package com.aoi.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @ClassName TestServiceTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/8 21:17
 */
@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    @Test
    public void test() {
        TestService mock = Mockito.mock(TestService.class);
        Mockito.when(mock.test()).thenReturn("test2");
        Assertions.assertEquals(mock.test(), "test2");
    }
}
