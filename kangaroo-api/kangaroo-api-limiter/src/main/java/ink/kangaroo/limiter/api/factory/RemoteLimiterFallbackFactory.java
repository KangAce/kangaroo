package ink.kangaroo.limiter.api.factory;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.limiter.api.RemoteLimiterService;
import ink.kangaroo.limiter.api.enums.TargetEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class RemoteLimiterFallbackFactory implements FallbackFactory<RemoteLimiterService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteLimiterFallbackFactory.class);

    @Override
    public RemoteLimiterService create(Throwable throwable) {
        log.error("令牌桶服务调用失败:{}", throwable.getMessage());
        return new RemoteLimiterService() {
            @Override
            public R<Long> acquire(TargetEnum targetEnum) {
                return null;
            }
        };
    }
}
