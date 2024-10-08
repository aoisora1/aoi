package com.aoi.core.exception;

import com.aoi.IntegrationTest;
import com.aoi.assembly.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ExceptionHandlerTest
 * @Description ExceptionHandlerTest
 * @Author 86184
 * @Date 2024/10/2 14:43
 */
@IntegrationTest
public class ExceptionHandlerTest {
    @Autowired
    ExceptionVoFactory exceptionVoFactory;

    @Test
    public void testException() {
        ExceptionVo test = exceptionVoFactory.getExceptionVo(new BusinessException("1001", "test"));
        Assertions.assertThat(test.getDescription().toString()).isEqualTo("{zh_CN=test不存在, en_US=test not exists}");
        Assertions.assertThat(test.getSolution().toString()).isEqualTo("{zh_CN=请联系管理员, en_US=Please contact the administrator}");
        Assertions.assertThat(test.getCode()).isEqualTo("1001");
    }
}
