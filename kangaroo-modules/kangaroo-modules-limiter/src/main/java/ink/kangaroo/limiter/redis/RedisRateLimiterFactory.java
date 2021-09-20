package ink.kangaroo.limiter.redis;

import ink.kangaroo.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/10 15:43
 */
@Slf4j
@Component
public class RedisRateLimiterFactory {


    private final RedisLockRegistry redisLockRegistry;
    private final RedisService redisService;

    /**
     * 本地持有对象
     */
    private final Map<String, RedisRateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    public RedisRateLimiterFactory(RedisLockRegistry redisLockRegistry, RedisService redisService) {
        this.redisLockRegistry = redisLockRegistry;
        this.redisService = redisService;
    }

    /**
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @return {@link RedisRateLimiter}
     */
    public RedisRateLimiter build(String key, Double permitsPerSecond, Integer maxBurstSeconds) {
        if (!rateLimiterMap.containsKey(key)) {
            synchronized (this) {
                if (!rateLimiterMap.containsKey(key)) {
                    rateLimiterMap.put(
                            key,
                            new RedisRateLimiter(key, permitsPerSecond, maxBurstSeconds, redisLockRegistry, redisService)
                    );
                }
            }
        }
        return rateLimiterMap.get(key);
    }
}
