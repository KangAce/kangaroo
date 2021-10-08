package ink.kangaroo.mail.api.factory;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.mail.api.RemoteMailService;
import ink.kangaroo.mail.api.model.param.MailParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 22:21
 */
@Component
public class RemoteMailFallbackFactory implements FallbackFactory<RemoteMailService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteMailFallbackFactory.class);
    @Override
    public RemoteMailService create(Throwable throwable) {
        log.error("邮件服务调用失败:{}", throwable.getMessage());
        return new RemoteMailService() {
            @Override
            public R<String> testMail(MailParam mailParam, String source) {
                return null;
            }
        };
    }
}
