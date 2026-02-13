package com.tianxiawushuang.constants.enumm;

import lombok.Getter;

/**
 * 职位枚举
 */
@Getter
public enum RoleEnum {
    /**
     * 用户
     */
    USER("normal_user", 1L),

    /**
     * 普通管理员
     */
    NORMAL_ADMIN("normal_admin", 2L),

    /**
     * 超级管理员
     */
    SUPER_ADMIN("super_admin", 3L);

    private final String key;

    private final Long value;

    RoleEnum(String key, Long value) {
        this.key = key;
        this.value = value;
    }
}
