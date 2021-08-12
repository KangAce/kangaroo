package ink.kangaroo.gateway.handler;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.utils.ServletUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.mail.api.RemoteMailService;
import ink.kangaroo.mail.api.model.param.MailParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 16:59
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);

    private final RemoteMailService remoteMailService;

    @Lazy
    @Autowired
    public GatewayExceptionHandler(RemoteMailService remoteMailService) {
        this.remoteMailService = remoteMailService;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof NotFoundException) {
            msg = "服务未找到";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
        } else {
            msg = "内部服务器错误";
        }
        MailParam mailParam = new MailParam();
        mailParam.setTo("26599114@qq.com");
        mailParam.setSubject("gateway出现异常");
        mailParam.setContent(StringUtils.format("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage()));
        remoteMailService.testMail(mailParam, SecurityConstants.INNER);
        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

        return ServletUtils.webFluxResponseWriter(response, msg);
    }
}