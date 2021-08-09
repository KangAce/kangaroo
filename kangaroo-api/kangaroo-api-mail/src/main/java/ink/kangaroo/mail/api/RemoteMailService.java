package ink.kangaroo.mail.api;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.constant.ServiceNameConstants;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.mail.api.factory.RemoteMailFallbackFactory;
import ink.kangaroo.mail.api.model.param.MailParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 22:20
 */
@FeignClient(contextId = "remoteMailService", value = ServiceNameConstants.MAIL_SERVICE, fallbackFactory = RemoteMailFallbackFactory.class)
public interface RemoteMailService {
    @PostMapping("/api/admin/mails/test")
    public AjaxResult testMail(@RequestBody MailParam mailParam, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
