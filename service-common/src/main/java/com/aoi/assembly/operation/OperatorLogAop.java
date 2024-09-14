package com.aoi.assembly.operation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OperatorLogAop
 * @Description 审计日志切面
 * @Author 86184
 * @Date 2024/9/14 20:36
 */
@Aspect
@Component
public class OperatorLogAop {
    private final Logger logger = LoggerFactory.getLogger(OperatorLogAop.class);

    @Autowired
    private SendLog sendLog;

    @Pointcut("@annotation(com.aoi.assembly.operation.OperationLog)")
    public void log() {

    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = joinPoint.proceed();

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog annotation = method.getAnnotation(OperationLog.class);
            String log = annotation.log();
            Parameter[] parameters = method.getParameters();
            List<Pair<String, String>> params = new ArrayList<>();
            for (int i = 0; i < parameters.length; i++) {
                if (!parameters[i].isAnnotationPresent(OperationParam.class)) {
                    continue;
                }
                OperationParam parameterAnnotation = parameters[i].getAnnotation(OperationParam.class);
                String paramName = StringUtils.isBlank(parameterAnnotation.value()) ? parameters[i].getName() : parameterAnnotation.value();
                params.add(Pair.of(paramName, joinPoint.getArgs()[i] == null ? "" : joinPoint.getArgs()[i].toString()));
            }
            String operatorLog = sendLog.concatLog(log, params);
            sendLog.sendLog(operatorLog);
        } catch (Exception e) {
            logger.error("send operator log error {}", e);
        }

        return res;
    }

}
