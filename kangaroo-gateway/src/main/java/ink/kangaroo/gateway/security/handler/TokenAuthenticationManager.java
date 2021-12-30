package ink.kangaroo.gateway.security.handler;

import ink.kangaroo.common.core.utils.JwtTokenUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * token 认证处理 自定义JWT Token认证管理
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:36
 */
@Component
@Primary
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> JwtTokenUtil.parseJwtRsa256(auth.getPrincipal().toString()))
                .map(claims -> {
                    List<LinkedHashMap<String, String>> roles = (List<LinkedHashMap<String, String>>) claims.get("roles");
                    System.out.println("roles:" + roles);
                    return new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            null,
                            roles.stream().map(e -> new SimpleGrantedAuthority(e.get("authority"))).collect(Collectors.toList())
                    );
                });
    }
}
