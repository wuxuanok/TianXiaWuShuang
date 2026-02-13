package com.tianxiawushuang.mapper;

import com.tianxiawushuang.pojo.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface LikeMapper extends BaseMapper<Like> {
    /**
     * 获取点赞数总和
     * @return 点赞数总和
     */
    Integer getLikeCount();
    
    /**
     * 检查用户是否已经存在
     * @param userIdentifier 用户标识
     * @return 是否已经存在
     */
    Integer checkUserLiked(String userIdentifier);
    
    /**
     * 添加点赞（如果用户存在则更新点赞次数，否则插入新记录）
     * @param userIdentifier 用户标识
     * @return 操作结果
     */
    int addLike(String userIdentifier);
}
