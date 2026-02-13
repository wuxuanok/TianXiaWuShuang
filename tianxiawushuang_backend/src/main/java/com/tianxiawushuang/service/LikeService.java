package com.tianxiawushuang.service;

import com.tianxiawushuang.pojo.vo.LikeCountVO;

public interface LikeService {
    /**
     * 获取点赞数
     * @return 点赞数VO
     */
    LikeCountVO getLikeCount();
    
    /**
     * 检查用户是否已经点赞
     * @param userIdentifier 用户标识
     * @return 是否已点赞
     */
    boolean checkUserLiked(String userIdentifier);
    
    /**
     * 添加点赞
     * @param userIdentifier 用户标识
     * @return 点赞数VO
     */
    LikeCountVO addLike(String userIdentifier);
}
