package ink.kangaroo.limiter.controller;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.security.annotation.InnerAuth;
import ink.kangaroo.limiter.RateLimiter;
import ink.kangaroo.limiter.api.enums.TargetEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("limiter")
public class LimiterController {

    @Resource
    RateLimiter limiter;

    @InnerAuth
    @RequestMapping("acquire")
    R<Long> acquire(TargetEnum targetEnum) {
        return R.ok(limiter.acquire(targetEnum));
    }
}
