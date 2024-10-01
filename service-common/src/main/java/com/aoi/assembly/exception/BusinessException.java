package com.aoi.assembly.exception;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/1 17:23
 */
public class BusinessException extends RuntimeException {
    private String code;
    private Object[] arguments;

    public BusinessException(String code, Object... arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
