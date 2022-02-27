package ink.kangaroo.gateway.security.handler;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 配置注销处理程序。 默认为SecurityContextServerLogoutHandler
 * 
 * @Classname DyOpenService
 * @description 描述
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@Component
@Slf4j
public class DefaultLogoutHandler implements ServerLogoutHandler {
    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {

        return Mono.defer(() -> Mono.just(exchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            Map<String, Object> map = new HashMap<>(3);
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            map.put(SecurityConstants.DETAILS_USER_ID, userDetails.getUserId());
            map.put(SecurityConstants.DETAILS_USERNAME, userDetails.getUsername());
            // TODO
            // userDetails.getAuthorities().add(new
            // SimpleGrantedAuthority("/pixiv/api/pixiv/test"));
            // userDetails.getAuthorities().add(new
            // SimpleGrantedAuthority("/pixiv/api/pixiv/wp"));
            // userDetails.getAuthorities().add(new SimpleGrantedAuthority("/try/try/try"));
            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/**"));
            // userDetails.getAuthorities().add(new SimpleGrantedAuthority("/douyin/**"));
            // userDetails.getAuthorities().add(new
            // SimpleGrantedAuthority("/swagger-ui/index.html"));
            map.put("roles", "userDetails.getAuthorities()");
            log.debug(map.toString());
            System.out.println("JSON.toJSONString(map):" + JSON.toJSONString(map));
            // String token = JwtTokenUtil.generateToken(map, userDetails.getUsername(),
            // jwtTokenExpired);
            // String refreshToken = JwtTokenUtil.generateToken(map,
            // userDetails.getUsername(), jwtTokenRefreshExpired);
            Map<String, Object> tokenMap = new HashMap<>(2);
            // System.out.println("token.length():" + token.length());
            // tokenMap.put("access_token", token);
            // tokenMap.put("expires_in", jwtTokenExpired);
            // tokenMap.put("refresh_token", refreshToken);
            // tokenMap.put("refresh_expires_in", jwtTokenRefreshExpired);
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSON.toJSONString(R.ok(tokenMap)).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
