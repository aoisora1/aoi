package com.aoi.assembly.operation;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DefaultSendLog
 * @Description sendlog默认实现
 * @Author 86184
 * @Date 2024/9/14 20:40
 */
// TODO 设计、对接告警中心
@Service
public class DefaultSendLog implements SendLog {
    private final Logger logger = LoggerFactory.getLogger(DefaultSendLog.class);

    @Override
    public void sendLog(String log) {
        logger.info("seng operator log, log is : {}", log);
    }

    @Override
    public String concatLog(String log, List<Pair<String, String>> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("operator log: ");
        sb.append(log);
        sb.append(".");
        if (CollectionUtils.isEmpty(params)) {
            return sb.toString();
        }
        sb.append("params: {");
        sb.append(
                params.stream().map((p) -> {
                    return p.getKey().concat(":").concat(p.getValue());
                }).collect(Collectors.joining(",")));
        sb.append("}.");

        return sb.toString();
    }
}
