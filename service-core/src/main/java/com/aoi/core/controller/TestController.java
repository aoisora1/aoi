package com.aoi.core.controller;

import com.aoi.assembly.operation.NoOperationLog;
import com.aoi.assembly.operation.OperationLog;
import com.aoi.assembly.operation.OperationParam;
import com.aoi.core.db.entity.TestTableEntity;
import com.aoi.core.service.TestService;
import com.aoi.core.service.TestTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/7 22:20
 */
@RestController
@RequestMapping("/test")
@Validated
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private TestTableService testTableService;

    @GetMapping("/x")
    @OperationLog(log = "测试方法")
    public String get(@OperationParam("kk") String key, String value) {
        logger.info("xxx");
        return testService.test();
    }

    @PostMapping("/x")
    @OperationLog(log = "测试方法")
    public void post() { }

    @PostMapping("/x2")
    @NoOperationLog
    public void post2() { }

    @GetMapping("/testTable/{id}")
    public TestTableEntity getTestTable(@PathVariable("id") String id) {
        return testTableService.queryById(id);
    }
}
