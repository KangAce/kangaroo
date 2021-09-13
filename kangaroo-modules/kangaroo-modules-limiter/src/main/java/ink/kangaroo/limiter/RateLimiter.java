package ink.kangaroo.limiter;

import ink.kangaroo.limiter.enums.TargetEnum;

import java.util.concurrent.TimeUnit;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/10 15:44
 */
public interface RateLimiter {


    /**
     * 获取一个许可，该方法会被阻塞直到获取到请求
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @return 等待时间
     */
    Long acquire(TargetEnum targetEnum);

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @param permits    需要获取的令牌数量
     * @return 等待时间
     */
    Long acquire(TargetEnum targetEnum, Long permits);

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @return 等待时间
     */
    Long acquire(String key, Double permitsPerSecond, Integer maxBurstSeconds);

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @param permits          令牌数量
     * @return 等待时间
     */
    Long acquire(String key, Double permitsPerSecond, Integer maxBurstSeconds, Long permits);

    /**
     * <p>获取一个许可数如果该许可数可以在不超过timeout的时间内获取得到的话，</p>
     * <p>或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）</p>
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @return 是否成功
     */
    Boolean tryAcquire(TargetEnum targetEnum);

    /**
     * <p>获取一个许可数如果该许可数可以在不超过timeout的时间内获取得到的话，</p>
     * <p>或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）</p>
     *
     * @param targetEnum {@link TargetEnum} 每分钟限制访问数的枚举类
     * @param fid        店铺ID
     * @return 是否成功
     */
    Boolean tryAcquire(TargetEnum targetEnum, String fid);

    /**
     * 获取指定数量许可，该方法会被阻塞直到获取到请求
     *
     * @param key              redis key
     * @param permitsPerSecond 每秒产生的令牌数
     * @param maxBurstSeconds  最大存储多少秒的令牌
     * @param permits          令牌数量
     * @param timeout          超时
     * @param timeUnit         时间单位{@link TimeUnit}
     * @return 等待时间
     */
    Boolean tryAcquire(String key, Double permitsPerSecond, Integer maxBurstSeconds, Long permits, Long timeout, TimeUnit timeUnit);
}
