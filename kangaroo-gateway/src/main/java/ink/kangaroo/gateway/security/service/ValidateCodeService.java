package ink.kangaroo.gateway.security.service;

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
