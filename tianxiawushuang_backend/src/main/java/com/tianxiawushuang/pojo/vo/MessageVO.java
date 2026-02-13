package com.tianxiawushuang.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 留言响应VO
 */
@Data
public class MessageVO {
    /**
     * 留言ID
     */
    private Long id;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
