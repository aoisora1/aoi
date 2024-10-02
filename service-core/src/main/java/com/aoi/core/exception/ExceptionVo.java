package com.aoi.core.exception;

import java.util.Locale;
import java.util.Map;

/**
 * @ClassName ExceptionVo
 * @Description Exception对象类
 * @Author 86184
 * @Date 2024/10/2 14:32
 */
public class ExceptionVo {
    private String code;
    private Map<Locale, String> description;
    private Map<Locale, String> solution;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Locale, String> getDescription() {
        return description;
    }

    public void setDescription(Map<Locale, String> description) {
        this.description = description;
    }

    public Map<Locale, String> getSolution() {
        return solution;
    }

    public void setSolution(Map<Locale, String> solution) {
        this.solution = solution;
    }
}
