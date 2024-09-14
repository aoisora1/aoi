package com.aoi.assembly.operator;

import com.aoi.assembly.operation.NoOperationLog;
import com.aoi.assembly.operation.OperationLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName OperatorLogTest
 * @Description 审计日志校验，涉及资源修改的controller方法都需要上报审计日志
 * @Author 86184
 * @Date 2024/9/14 21:36
 */
public class OperatorLogTest {

    @Test
    public void operatorLogCheck() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.aoi");
        Set<Class<?>> controllers = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (BeanDefinition candidate : candidateComponents) {
            controllers.add(Class.forName(candidate.getBeanClassName()));
        }
        for (Class<?> clazz : controllers) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!isLogMethod(method)) {
                    continue;
                }
                if (!method.isAnnotationPresent(OperationLog.class) && !method.isAnnotationPresent(NoOperationLog.class)) {
                    res.add(clazz.getSimpleName() + "." + method.getName());
                }
            }
        }

        System.out.println(res);
        Assertions.assertTrue(res.isEmpty());
    }

    private boolean isLogMethod(Method method) {
        if (method.isAnnotationPresent(PutMapping.class) ||
                method.isAnnotationPresent(PatchMapping.class) ||
                method.isAnnotationPresent(PostMapping.class) ||
                method.isAnnotationPresent(DeleteMapping.class)
        ) {
            return true;
        }
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            RequestMethod[] methods = annotation.method();
            for (RequestMethod pathMethod : methods) {
                if (RequestMethod.POST == pathMethod || RequestMethod.PUT == pathMethod || RequestMethod.DELETE == pathMethod || RequestMethod.PATCH == pathMethod) {
                    return true;
                }
            }
        }
        return false;
    }
}
