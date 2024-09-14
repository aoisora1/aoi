package com.aoi.core.controller;

import com.aoi.core.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @ClassName TestControllerTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/14 18:55
 */
@ExtendWith(MockitoExtension.class)
public class TestControllerTest {
    @InjectMocks
    private TestController testController;

    @Mock
    private TestService testService;

    @Test
    public void test() {
        Mockito.when(testService.test()).thenReturn("test2");
        Assertions.assertEquals(testController.get("", ""), "test2");
    }
}
