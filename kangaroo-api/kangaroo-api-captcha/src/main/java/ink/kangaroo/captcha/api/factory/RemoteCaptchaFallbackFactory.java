package ink.kangaroo.captcha.api.factory;

import ink.kangaroo.captcha.api.RemoteCaptchaService;
import ink.kangaroo.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:27
 */
@Component
public class RemoteCaptchaFallbackFactory implements FallbackFactory<RemoteCaptchaService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteCaptchaFallbackFactory.class);

    @Override
    public RemoteCaptchaService create(Throwable throwable) {
        log.error("验证码服务调用失败:{}", throwable.getMessage());
        return new RemoteCaptchaService() {
            @Override
            public R<Object> captchaVerification(String captchaVerification) {
                return R.fail("验证码验证失败:" + throwable.getMessage());
            }
        };
    }
}