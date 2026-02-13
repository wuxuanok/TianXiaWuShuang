package com.tianxiawushuang.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplateTemp;

    @PostConstruct
    public void init() {
        RedisUtils.redisTemplate = this.redisTemplateTemp;
    }

    /**
     * 通用缓存查询方法（简化重复的Redis操作）
     * @param cacheKey 缓存Key
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     * @param dbLoader DB查询逻辑（函数式接口）
     * @return 缓存/DB中的数据
     */
    public static <T> T getCacheData(String cacheKey, long expireTime, TimeUnit timeUnit, Supplier<T> dbLoader) {
        // 1. 查缓存
        try {
            Object cacheObj = redisTemplate.opsForValue().get(cacheKey);
            if (cacheObj != null) {
                log.info("从Redis缓存获取数据，Key：{}", cacheKey);
                if (cacheObj instanceof Integer && Long.class.isAssignableFrom(((Class<T>) ((ParameterizedType) dbLoader.getClass().getGenericSuperclass()).getActualTypeArguments()[0]))) {
                    return (T) Long.valueOf(((Integer) cacheObj).longValue());
                }
                return (T) cacheObj;
            }
        } catch (Exception e) {
            log.error("Redis获取缓存异常，Key：{}", cacheKey, e);
        }

        // 2. 查DB
        T data = dbLoader.get();

        // 3. 写缓存（异常不影响业务）
        try {
            redisTemplate.opsForValue().set(cacheKey, data, expireTime, timeUnit);
            log.info("DB数据写入Redis缓存，Key：{}，过期时间：{} {}", cacheKey, expireTime, timeUnit);
        } catch (Exception e) {
            log.error("Redis写入缓存异常，Key：{}", cacheKey, e);
        }
        return data;
    }
}
