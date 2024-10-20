package com.aoi.core.chain;

import java.util.List;

/**
 * @ClassName ChainManager
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:21
 */
public interface ChainManager {
    void setPlugins(List<Plugin> plugins);

    void run(ChainContext context);
}
