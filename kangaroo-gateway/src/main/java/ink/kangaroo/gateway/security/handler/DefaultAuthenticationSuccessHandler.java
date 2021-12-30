package ink.kangaroo.gateway.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.JwtTokenUtil;
import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理 自定义登录成功Handler
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:27
 */
@Component
public class DefaultAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    /**
     * token 过期时间
     */
    @Value("${security.jwt.token.expired}")
    private Long jwtTokenExpired;

    /**
     * 刷新token 时间
     */
    @Value("${security.jwt.token.refresh.expired}")
    private Long jwtTokenRefreshExpired;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            Map<String, Object> map = new HashMap<>(3);
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            map.put(SecurityConstants.DETAILS_USER_ID, userDetails.getUserId());
            map.put(SecurityConstants.DETAILS_USERNAME, userDetails.getUsername());
            //TODO
//            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/pixiv/api/pixiv/test"));
//            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/pixiv/api/pixiv/wp"));
//            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/try/try/try"));
            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/**"));
//            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/douyin/**"));
//            userDetails.getAuthorities().add(new SimpleGrantedAuthority("/swagger-ui/index.html"));
            map.put("roles", "userDetails.getAuthorities()");
            System.out.println("JSON.toJSONString(map):" + JSON.toJSONString(map));
            String token = JwtTokenUtil.generateToken(map, userDetails.getUsername(), jwtTokenExpired);
            String refreshToken = JwtTokenUtil.generateToken(map, userDetails.getUsername(), jwtTokenRefreshExpired);
            Map<String, Object> tokenMap = new HashMap<>(2);
            System.out.println("token.length():" + token.length());
            tokenMap.put("access_token", token);
            tokenMap.put("expires_in", jwtTokenExpired);
            tokenMap.put("refresh_token", refreshToken);
            tokenMap.put("refresh_expires_in", jwtTokenRefreshExpired);
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(R.ok(tokenMap)).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}