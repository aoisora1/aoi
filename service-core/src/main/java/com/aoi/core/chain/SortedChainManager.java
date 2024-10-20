package com.aoi.core.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName DefaultChainManager
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:23
 */
public class SortedChainManager implements ChainManager {
    private final Logger logger = LoggerFactory.getLogger(SortedChainManager.class);

    private List<Plugin> plugins;

    @Override
    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    protected void sortPlugins() {
        plugins.sort((a, b) -> {
            return a.getOrder() - b.getOrder();
        });
    }

    @Override
    public void run(ChainContext context) {
        plugins.forEach(p -> {
            logger.info("before execute handler {}", p.getClass());
            p.handle(context);
            logger.info("after execute handler {}", p.getClass());
        });
    }
}
