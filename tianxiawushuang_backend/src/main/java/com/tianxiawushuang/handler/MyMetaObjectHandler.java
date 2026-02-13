package com.tianxiawushuang.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充创建时间和更新时间
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(
                metaObject,
                CREATE_TIME,
                LocalDateTime.class,
                LocalDateTime.now()
        );
        this.strictInsertFill(
                metaObject,
                UPDATE_TIME,
                LocalDateTime.class,
                LocalDateTime.now()
        );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(
                metaObject,
                UPDATE_TIME,
                LocalDateTime.class,
                LocalDateTime.now()
        );
    }
}
