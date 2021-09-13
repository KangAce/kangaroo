package ink.kangaroo.limiter.redis;

import ink.kangaroo.common.redis.service.RedisService;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/10 15:42
 */
public class RedisRateLimiter {

    private static final String PERMIT_KEY = "permit:";
    private static final String LOCK_KEY = "lock:";

    /**
     * 分布式同步锁
     */
    private final RedisLockRegistry syncLock;
    private final RedisService redisService;
    /**
     * redis key
     */
    private String key;
    /**
     * redis分布式锁的key
     */
    private String lockKey;
    /**
     * 每秒存入的令牌数
     */
    private Double permitsPerSecond;
    /**
     * 最大存储maxBurstSeconds秒生成的令牌
     */
    private Integer maxBurstSeconds;

    public RedisRateLimiter(String key, Double permitsPerSecond, Integer maxBurstSeconds,
                            RedisLockRegistry syncLock, RedisService redisService) {
        this.key = PERMIT_KEY.concat(key);
        this.lockKey = LOCK_KEY.concat(key);
        this.permitsPerSecond = permitsPerSecond;
        this.maxBurstSeconds = maxBurstSeconds;
        this.syncLock = syncLock;
        this.redisService = redisService;
    }

    /**
     * 生成并存储默认令牌桶
     *
     * @return {@link RedisPermits}
     */
    private RedisPermits putDefaultPermits() {
        Lock obtain = syncLock.obtain(lockKey);
        obtain.lock();
        try {
            RedisPermits value = redisService.getCacheObject(key);
            if (StringUtils.isEmpty(value)) {
                RedisPermits permits = new RedisPermits(permitsPerSecond, maxBurstSeconds);
                redisService.setCacheObject(key, permits, permits.expires(), TimeUnit.SECONDS);
                return permits;
            } else {
                return value;
            }
        } finally {
            obtain.unlock();
        }

    }

    /**
     * 获取令牌桶
     *
     * @return {@link RedisPermits}
     */
    public RedisPermits getPermits() {
        RedisPermits value = redisService.getCacheObject(key);
        if (StringUtils.isEmpty(value)) {
            return putDefaultPermits();
        }
        return value;
    }

    /**
     * 更新令牌桶
     *
     * @param permits {@link RedisPermits}
     */
    public void setPermits(RedisPermits permits) {
        redisService.setCacheObject(key, permits, permits.expires(), TimeUnit.SECONDS);
    }

    /**
     * 获取指定数量的令牌需要的等待时间
     *
     * @param tokens 数量
     * @return 等待时间
     * @throws InterruptedException 异常
     */
    public Long acquire(Long tokens) throws InterruptedException {
        long milliToWait = this.reserve(tokens);
        Thread.sleep(milliToWait);
        return milliToWait;
    }

    /**
     * 获取一个令牌需要的等待时间
     *
     * @return 等待时间
     * @throws InterruptedException 异常
     */
    public Long acquire() throws InterruptedException {
        return acquire(1L);
    }

    /**
     * 尝试获取单位时间内令牌
     *
     * @param tokens  要获取的令牌数
     * @param timeout 获取令牌等待的时间，负数被视为0
     * @param unit    时间单位
     * @return 是否获取
     * @throws InterruptedException 异常
     */
    public Boolean tryAcquire(Long tokens, Long timeout, TimeUnit unit) throws InterruptedException {
        long timeoutMicros = Math.max(unit.toMillis(timeout), 0);
        checkTokens(tokens);
        long milliToWait;
        Lock obtain = syncLock.obtain(lockKey);
        obtain.lock();
        try {
            if (!this.canAcquire(tokens, timeoutMicros)) {
                return false;
            } else {
                milliToWait = this.reserveAndGetWaitLength(tokens);
            }
        } finally {
            obtain.unlock();
        }
        Thread.sleep(milliToWait);
        return true;
    }

    /**
     * 获取一个令牌
     *
     * @param timeout 超时
     * @param unit    时间单位
     * @return 是否成功
     * @throws InterruptedException 异常
     */
    public Boolean tryAcquire(Long timeout, TimeUnit unit) throws InterruptedException {
        return tryAcquire(1L, timeout, unit);
    }

    private long redisNow() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取令牌n个需要等待的时间
     *
     * @param tokens 数量n
     * @return 等待时间
     */
    private long reserve(Long tokens) {
        this.checkTokens(tokens);
        Lock obtain = syncLock.obtain(lockKey);
        obtain.lock();
        try {
            return this.reserveAndGetWaitLength(tokens);
        } finally {
            obtain.unlock();
        }
    }

    /**
     * 校验token值
     *
     * @param tokens tokens
     */
    private void checkTokens(Long tokens) {
        if (tokens < 0) {
            throw new IllegalArgumentException("Requested tokens " + tokens + " must be positive");
        }
    }

    /**
     * 在等待的时间内是否可以获取到令牌
     *
     * @param tokens        tokens
     * @param timeoutMillis timeoutMillis
     * @return 是否成功
     */
    private Boolean canAcquire(Long tokens, Long timeoutMillis) {
        return queryEarliestAvailable(tokens) - timeoutMillis <= 0;
    }

    /**
     * 返回获取{tokens}个令牌最早可用的时间
     *
     * @param tokens tokens
     * @return 时间
     */
    private Long queryEarliestAvailable(Long tokens) {
        long n = redisNow();
        RedisPermits permit = this.getPermits();
        permit.reSync(n);
        // 可以消耗的令牌数
        long storedPermitsToSpend = Math.min(tokens, permit.getStoredPermits());
        // 需要等待的令牌数
        long freshPermits = tokens - storedPermitsToSpend;
        // 需要等待的时间
        long waitMillis = freshPermits * permit.getIntervalMillis();
        return LongMath.saturatedAdd(permit.getNextFreeTicketMillis() - n, waitMillis);
    }

    /**
     * 预定@{tokens}个令牌并返回所需要等待的时间
     *
     * @param tokens tokens
     * @return 时间
     */
    private Long reserveAndGetWaitLength(Long tokens) {
        long now = redisNow();
        RedisPermits permit = this.getPermits();
        permit.reSync(now);
        // 可以消耗的令牌数
        long storedPermitsToSpend = Math.min(tokens, permit.getStoredPermits());
        // 需要等待的令牌数
        long freshPermits = tokens - storedPermitsToSpend;
        // 需要等待的时间
        long waitMillis = freshPermits * permit.getIntervalMillis();
        permit.setNextFreeTicketMillis(LongMath.saturatedAdd(permit.getNextFreeTicketMillis(), waitMillis));
        permit.setStoredPermits(permit.getStoredPermits() - storedPermitsToSpend);
        this.setPermits(permit);
        return permit.getNextFreeTicketMillis() - now;
    }

}
