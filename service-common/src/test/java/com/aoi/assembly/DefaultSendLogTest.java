package com.aoi.assembly;

import com.aoi.assembly.operation.DefaultSendLog;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DefaultSendLogTest
 * @Description DefaultSendLogTest
 * @Author 86184
 * @Date 2024/9/14 21:06
 */
@ExtendWith(MockitoExtension.class)
public class DefaultSendLogTest {
    @InjectMocks
    private DefaultSendLog defaultSendLog;

    @Test
    public void logConcatTest() {
        String log = "add user";
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(Pair.of("username", "test"));
        params.add(Pair.of("userid", "1"));

        String sendLog = defaultSendLog.concatLog(log, params);
        Assertions.assertEquals(sendLog, "operator log: add user.params: {username:test,userid:1}.");
    }
}
