package ink.kangaroo.limiter.api;

import ink.kangaroo.common.core.constant.ServiceNameConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.limiter.api.enums.TargetEnum;
import ink.kangaroo.limiter.api.factory.RemoteLimiterFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 22:20
 */
@FeignClient(contextId = "RemoteLimiterService", value = ServiceNameConstants.LIMITER_SERVICE, fallbackFactory = RemoteLimiterFallbackFactory.class)
public interface RemoteLimiterService {
    @PostMapping("/limiter/acquire")
    R<Long> acquire(TargetEnum targetEnum);
}
