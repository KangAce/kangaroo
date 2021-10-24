package ink.kangaroo.gateway.handler;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.DecimalUtils;
import ink.kangaroo.common.core.utils.ExceptionUtil;
import ink.kangaroo.common.core.utils.ServletUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.mail.api.RemoteMailService;
import ink.kangaroo.mail.api.model.param.MailParam;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

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
    private Executor executor;

    @Lazy
    @Autowired
    public GatewayExceptionHandler(RemoteMailService remoteMailService, @Qualifier("customizeThreadPool") Executor executor) {
        this.remoteMailService = remoteMailService;
        this.executor = executor;
    }

    @Override
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof NotFoundException) {
            msg = "服务未找到";
        } else if (ex instanceof SignatureException) {
            msg = "Token 错误";
        } else if (ex instanceof ExpiredJwtException) {
            msg = "Token 已过期";
        } else if (ex instanceof StringIndexOutOfBoundsException) {
            msg = "Token 长度不足";
        } else if (ex instanceof MalformedJwtException) {
            msg = "Token Error";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
        } else {
            msg = "内部服务器错误";
            MailParam mailParam = new MailParam();
            mailParam.setTo("26599114@qq.com");
            mailParam.setSubject("gateway出现异常");
            String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
            mailParam.setContent(StringUtils.format("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), exceptionMessage));
            sendMail(mailParam);
        }

        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ExceptionUtil.getExceptionMessage(ex));

        return ServletUtils.webFluxResponseWriter(response, msg);
    }

    R<String> sendMail(MailParam mailParam) {
        //因为Callable接口是函数式接口，可以使用Lambda表达式
        FutureTask<R<String>> task = new FutureTask<>(() -> remoteMailService.testMail(mailParam, SecurityConstants.INNER + ":" + DecimalUtils._10_to_N(System.currentTimeMillis(), 62)));
        executor.execute(task);
        try {
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}