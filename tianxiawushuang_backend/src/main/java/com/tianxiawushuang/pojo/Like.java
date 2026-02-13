package com.tianxiawushuang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("`like`")
public class Like {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userIdentifier;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
