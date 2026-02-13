package com.tianxiawushuang.controller;

import com.tianxiawushuang.common.Result;
import com.tianxiawushuang.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {
    
    private static final Logger logger = LoggerFactory.getLogger(LikeController.class);
    
    @Autowired
    private LikeService likeService;
    
    /**
     * 获取点赞数
     * @return 点赞数响应
     */
    @GetMapping
    public Result<?> getLikeCount() {
        logger.info("获取点赞数请求");
        try {
            return Result.ok(likeService.getLikeCount());
        } catch (Exception e) {
            logger.error("获取点赞数失败：", e);
            return Result.error("获取点赞数失败");
        }
    }
    
    /**
     * 检查用户是否已经点赞
     * @param request 请求参数
     * @return 检查结果响应
     */
    @PostMapping("/check")
    public Result<?> checkUserLiked(@RequestBody Map<String, Object> request) {
        String userIdentifier = (String) request.get("userIdentifier");
        logger.info("检查用户是否已经点赞请求，用户标识：{}", userIdentifier);
        try {
            boolean liked = likeService.checkUserLiked(userIdentifier);
            return Result.ok(liked);
        } catch (Exception e) {
            logger.error("检查用户是否已经点赞失败：", e);
            return Result.error("检查用户是否已经点赞失败");
        }
    }
    
    /**
     * 增加点赞数
     * @param request 请求参数
     * @return 点赞数响应
     */
    @PostMapping
    public Result<?> addLike(@RequestBody Map<String, Object> request) {
        String userIdentifier = (String) request.get("userIdentifier");
        logger.info("增加点赞数请求，用户标识：{}", userIdentifier);
        try {
            return Result.ok(likeService.addLike(userIdentifier));
        } catch (Exception e) {
            logger.error("增加点赞数失败：", e);
            return Result.error("增加点赞数失败");
        }
    }
}
