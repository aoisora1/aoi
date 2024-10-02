package com.aoi.core.config;

import com.aoi.assembly.exception.BusinessException;
import com.aoi.core.exception.ExceptionService;
import com.aoi.core.exception.ExceptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author 86184
 * @Date 2024/10/2 15:58
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private ExceptionService exceptionService;

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ExceptionVo handleBusinessException(BusinessException e) {
        return exceptionService.getExceptionVo(e);
    }

    // 兜底异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ExceptionVo handleException(Exception e) {
        return exceptionService.getExceptionVo(new BusinessException("500"));
    }
}
