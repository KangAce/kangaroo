package ink.kangaroo.captcha.api;

import ink.kangaroo.captcha.api.factory.RemoteCaptchaFallbackFactory;
import ink.kangaroo.common.core.constant.ServiceNameConstants;
import ink.kangaroo.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:19
 */
@FeignClient(contextId = "remoteCaptchaService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteCaptchaFallbackFactory.class)
public interface RemoteCaptchaService {
    /**
     * 上传文件
     *
     * @param captchaVerification 验证码
     * @return 结果
     */
    @PostMapping(value = "/auth/captchaVerification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Object> captchaVerification(@RequestPart(value = "captchaVerification") String captchaVerification);
}
