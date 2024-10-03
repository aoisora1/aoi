package com.aoi.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName JsonUtil
 * @Description json
 * @Author 86184
 * @Date 2024/10/3 14:14
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonNode readTree(String json) throws JsonProcessingException {
        return MAPPER.readTree(json);
    }
}
