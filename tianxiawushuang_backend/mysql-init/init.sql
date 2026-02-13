-- 创建数据库
CREATE DATABASE IF NOT EXISTS tianxiawushuang CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE tianxiawushuang;

-- 创建留言表
CREATE TABLE IF NOT EXISTS message (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL COMMENT '留言内容',
    user_identifier VARCHAR(255) NOT NULL COMMENT '用户标识（如IP地址、浏览器信息等）',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_create_time (create_time),
    INDEX idx_user_identifier (user_identifier)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='留言表';

-- 创建点赞表
CREATE TABLE IF NOT EXISTS `like` (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_identifier VARCHAR(255) NOT NULL COMMENT '用户标识（如IP地址、浏览器信息等）',
    like_count INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '点赞次数',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE INDEX idx_user_identifier (user_identifier) COMMENT '每个用户一条记录'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- 初始化点赞表，不需要插入记录，用户点赞时自动插入
