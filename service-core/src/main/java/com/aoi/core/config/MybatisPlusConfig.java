package com.aoi.core.config;

import com.aoi.core.util.UserHolder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

/**
 * @ClassName MybatisPlusConfig
 * @Description 配置MybatisPlus
 * @Author 86184
 * @Date 2024/10/1 16:21
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MyMetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }

    public class MyMetaObjectHandler implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
            this.strictInsertFill(metaObject, "createUser", String.class, UserHolder.userName()); // TODO
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
            this.strictUpdateFill(metaObject, "updateUser", String.class, UserHolder.userName()); // TODO
        }
    }
}
