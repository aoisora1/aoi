package com.aoi.core.chain.test;

import com.aoi.core.chain.ChainConstant;
import com.aoi.core.chain.Plugin;
import com.aoi.core.chain.SortedChainManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName TestChainManager
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:38
 */
@Component
public class TestChainManager extends SortedChainManager {

    public TestChainManager(@Qualifier(ChainConstant.TEST_PLUGIN) List<Plugin> plugins) {
        setPlugins(plugins);
    }
}
