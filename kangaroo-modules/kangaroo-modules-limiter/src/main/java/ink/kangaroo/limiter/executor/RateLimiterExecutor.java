package ink.kangaroo.limiter.executor;

import ink.kangaroo.limiter.RateLimiter;
import ink.kangaroo.limiter.enums.TargetEnum;
import ink.kangaroo.limiter.redis.RedisRateLimiterFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/10 15:39
 */
@Slf4j
public class RateLimiterExecutor  implements RateLimiter {

    private static final int SIXTY = 60;

    private final RedisRateLimiterFactory redisRateLimiterFactory;

    public RateLimiterExecutor(RedisRateLimiterFactory redisRateLimiterFactory) {
        this.redisRateLimiterFactory = redisRateLimiterFactory;
    }

    /**
     * 获取一个许可，该方法会被阻塞直到获取到请求
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @return 等待时间
     */
    @Override
    public Long acquire(TargetEnum targetEnum) {
        return acquire(targetEnum, 1L);
    }

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param targetEnum {@link TargetEnum}
     * @param permits    需要获取的令牌数量
     * @return 等待时间
     */
    @Override
    public Long acquire(TargetEnum targetEnum, Long permits) {
        Double permitsPerSecond = (double) targetEnum.getLimit() / SIXTY;
        return acquire(targetEnum.name().toLowerCase(), permitsPerSecond, SIXTY, permits);
    }

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @return 等待时间
     */
    @Override
    public Long acquire(String key, Double permitsPerSecond, Integer maxBurstSeconds) {
        return acquire(key, permitsPerSecond, maxBurstSeconds, 1L);
    }

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @param permits          令牌数量
     * @return 等待时间
     */
    @Override
    public Long acquire(String key, Double permitsPerSecond, Integer maxBurstSeconds, Long permits) {
        try {
            return redisRateLimiterFactory.build(
                    key.toLowerCase(),
                    permitsPerSecond,
                    maxBurstSeconds
            ).acquire(permits);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * <p>获取一个许可数如果该许可数可以在不超过timeout的时间内获取得到的话，</p>
     * <p>或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）</p>
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @return 是否成功
     */
    @Override
    public Boolean tryAcquire(TargetEnum targetEnum) {
        return tryAcquire(targetEnum, "default");
    }

    @Override
    public Boolean tryAcquire(TargetEnum targetEnum, String fid) {
        Double permitsPerSecond = (double) targetEnum.getLimit() / SIXTY;
        return tryAcquire(targetEnum.name().concat(":").concat(fid), permitsPerSecond,
                1, 1L, 1L, TimeUnit.SECONDS);
    }

    @Override
    public Boolean tryAcquire(String key, Double permitsPerSecond, Integer maxBurstSeconds, Long permits, Long timeout, TimeUnit timeUnit) {
        try {
            return redisRateLimiterFactory.build(
                    key.toLowerCase(),
                    permitsPerSecond,
                    maxBurstSeconds
            ).tryAcquire(permits, timeout, timeUnit);
        } catch (InterruptedException e) {
            log.error("服务器异常", e);
        }
        return false;
    }
}
