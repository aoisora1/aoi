package com.aoi.assembly.exception;

import java.util.Locale;

/**
 * @ClassName BusinessException
 * @Description 业务异常
 * @Author 86184
 * @Date 2024/10/1 17:23
 */
public class BusinessException extends RuntimeException {
    private String code;
    private Object[] args;

    public BusinessException(String code, Object... args) {
        super(String.format(Locale.ROOT, "code=%s, args=%s", code, args));
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
