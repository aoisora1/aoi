package com.aoi.core.chain.test;

import com.aoi.core.chain.ChainConstant;
import com.aoi.core.chain.ChainContext;
import com.aoi.core.chain.Plugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestPluginOne
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:34
 */
@Component
@Qualifier(ChainConstant.TEST_PLUGIN)
public class TestPluginTwo implements Plugin {
    @Override
    public void handle(ChainContext context) {
        TestChainContext ctx = (TestChainContext) context;
        ctx.setName(ctx.getName().concat("2"));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
