package com.tianxiawushuang.service.impl;

import com.tianxiawushuang.pojo.vo.LikeCountVO;
import com.tianxiawushuang.mapper.LikeMapper;
import com.tianxiawushuang.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    
    private static final Logger logger = LoggerFactory.getLogger(LikeServiceImpl.class);
    
    @Autowired
    private LikeMapper likeMapper;
    
    @Override
    public LikeCountVO getLikeCount() {
        try {
            Integer count = likeMapper.getLikeCount();
            LikeCountVO vo = new LikeCountVO();
            vo.setCount(count != null ? count : 0);
            logger.info("获取点赞数成功：{}", count);
            return vo;
        } catch (Exception e) {
            logger.error("获取点赞数失败：", e);
            LikeCountVO vo = new LikeCountVO();
            vo.setCount(0);
            return vo;
        }
    }
    
    @Override
    public boolean checkUserLiked(String userIdentifier) {
        try {
            Integer count = likeMapper.checkUserLiked(userIdentifier);
            logger.info("检查用户是否已经点赞：{}，结果：{}", userIdentifier, count > 0);
            return count > 0;
        } catch (Exception e) {
            logger.error("检查用户是否已经点赞失败：", e);
            return false;
        }
    }
    
    @Override
    public LikeCountVO addLike(String userIdentifier) {
        try {
            // 添加点赞（数据库层面会处理用户存在时更新点赞次数，不存在时插入新记录）
            int result = likeMapper.addLike(userIdentifier);
            logger.info("添加点赞成功，用户标识：{}，影响行数：{}", userIdentifier, result);
            return getLikeCount();
        } catch (Exception e) {
            logger.error("添加点赞失败：", e);
            return getLikeCount();
        }
    }
}
