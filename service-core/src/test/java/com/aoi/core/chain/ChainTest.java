package com.aoi.core.chain;

import com.aoi.IntegrationTest;
import com.aoi.core.chain.test.TestChainContext;
import com.aoi.core.chain.test.TestChainManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ChainTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:40
 */
@IntegrationTest
public class ChainTest {
    @Autowired
    TestChainManager manager;

    @Test
    public void test() {
        TestChainContext context = new TestChainContext();
        context.setName("test");

        manager.run(context);
        Assertions.assertThat(context.getName()).isEqualTo("TEST23");
    }
}
