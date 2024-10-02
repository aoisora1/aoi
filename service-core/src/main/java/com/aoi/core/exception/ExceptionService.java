package com.aoi.core.exception;

import com.aoi.assembly.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName ExceptionHandler
 * @Description 异常信息处理
 * @Author 86184
 * @Date 2024/10/2 14:30
 */
@Component
public class ExceptionService {

    @Autowired
    private MessageSource messageSource;

    public ExceptionVo getExceptionVo(BusinessException exception) {
        ExceptionVo exceptionVo = new ExceptionVo();
        exceptionVo.setCode(exception.getCode());
        // TODO 不应该是手动写local，而是根据所有配置自动循环
        Map<Locale, String> descriptionMap = new HashMap<>();
        descriptionMap.put(Locale.SIMPLIFIED_CHINESE, messageSource.getMessage("description." + exception.getCode(), exception.getArguments(), Locale.SIMPLIFIED_CHINESE));
        descriptionMap.put(Locale.US, messageSource.getMessage("description." + exception.getCode(), exception.getArguments(), Locale.US));
        exceptionVo.setDescription(descriptionMap);

        Map<Locale, String> solutionMap = new HashMap<>();
        solutionMap.put(Locale.SIMPLIFIED_CHINESE, messageSource.getMessage("solution." + exception.getCode(), exception.getArguments(), Locale.SIMPLIFIED_CHINESE));
        solutionMap.put(Locale.US, messageSource.getMessage("solution." + exception.getCode(), exception.getArguments(), Locale.US));
        exceptionVo.setSolution(solutionMap);
        return exceptionVo;
    }
}
