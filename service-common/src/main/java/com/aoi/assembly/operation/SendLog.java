package com.aoi.assembly.operation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @ClassName SendLog
 * @Description 审计日志上报接口
 * @Author 86184
 * @Date 2024/9/14 20:38
 */
public interface SendLog {
    void sendLog(String log);

    String concatLog(String log, List<Pair<String, String>> params);
}
