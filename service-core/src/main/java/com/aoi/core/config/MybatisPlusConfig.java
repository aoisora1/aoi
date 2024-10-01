package com.aoi.core.config;

import com.aoi.core.util.UserHolder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/1 16:21
 */
public class MybatisPlusConfig {


    static class MyMetaObjectHandler implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, "createUser", String.class, UserHolder.userName());
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            this.strictUpdateFill(metaObject, "updateUser", String.class, UserHolder.userName()); // TODO
        }
    }
}
