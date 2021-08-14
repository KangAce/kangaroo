package ink.kangaroo.gateway.security.handler;

import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.enums.ResultEnums;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.gateway.security.service.SysLoginService;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 登录失败处理 自定义登录失败Handler
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:28
 */
@Component
public class DefaultAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private SysLoginService sysLoginService;

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange()
                .getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            AjaxResult resultVO = AjaxResult.fail("");
            // 账号不存在
            if (exception instanceof UsernameNotFoundException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_NOT_EXIST);
                // 用户名或密码错误
            } else if (exception instanceof BadCredentialsException) {
                resultVO = AjaxResult.of(ResultEnums.LOGIN_PASSWORD_ERROR);
                // 账号已过期
            } else if (exception instanceof AccountExpiredException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_EXPIRED);
                // 账号已被锁定
            } else if (exception instanceof LockedException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_LOCKED);
                // 用户凭证已失效
            } else if (exception instanceof CredentialsExpiredException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_CREDENTIAL_EXPIRED);
                // 账号已被禁用
            } else if (exception instanceof DisabledException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof AccountStatusException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof AuthenticationServiceException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof CookieTheftException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof InvalidCookieException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof RememberMeAuthenticationException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof InsufficientAuthenticationException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof NonceExpiredException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof PreAuthenticatedCredentialsNotFoundException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof ProviderNotFoundException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            } else if (exception instanceof SessionAuthenticationException) {
                resultVO = AjaxResult.of(ResultEnums.ACCOUNT_DISABLE);
                // 账号已被禁用
            }
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(resultVO).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}