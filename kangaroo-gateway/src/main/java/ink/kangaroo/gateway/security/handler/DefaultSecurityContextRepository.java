package ink.kangaroo.gateway.security.handler;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 存储认证授权的相关信息 自定义JWT Token认证管理
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:34
 */
@Slf4j
@Component
public class DefaultSecurityContextRepository implements ServerSecurityContextRepository {

    private final TokenAuthenticationManager tokenAuthenticationManager;

    public DefaultSecurityContextRepository(TokenAuthenticationManager tokenAuthenticationManager) {
        this.tokenAuthenticationManager = tokenAuthenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        //获取头信息，并处理
        ServerHttpRequest request = exchange.getRequest();
        List<String> headers = request.getHeaders().get(SecurityConstants.TOKEN_AUTHENTICATION);
        if (!CollectionUtils.isEmpty(headers)) {
            String authorization = headers.get(0);
            if (StringUtils.isNotEmpty(authorization)) {
                String token = authorization.substring(SecurityConstants.TOKEN_PREFIX.length());
                if (StringUtils.isNotEmpty(token)) {
                    return tokenAuthenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(token, null)
                    ).map(SecurityContextImpl::new);
                }
            }
        }
        return Mono.empty();
    }
}