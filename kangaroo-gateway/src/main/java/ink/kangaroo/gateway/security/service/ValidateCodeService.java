package ink.kangaroo.gateway.security.service;

import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 * 
 * @author ruoyi
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCapcha()throws IOException;

    /**
     * 校验验证码
     */
    public void checkCapcha(String key, String value)throws IOException ;
}
