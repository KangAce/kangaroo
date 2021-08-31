package ink.kangaroo.gateway.security.handler;

import ink.kangaroo.common.redis.service.RedisService;
import ink.kangaroo.gateway.security.SliderVerificationCodeType;
import ink.kangaroo.gateway.security.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/30 20:01
 */
public class EmailCodeAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {
    @Autowired
    ValidateService validateService;
    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        String[] split = username.split(";");
        if (split.length>5){
            throw new UsernameNotFoundException("验证码获取错误");
        }
        String mobile = split[0];
        String uuid = split[1];
        Integer X = Integer.valueOf(split[2]);
        Integer Y = Integer.valueOf(split[3]);
        Integer type = Integer.valueOf(split[4]);
        validateService.checkSlider(uuid,X,Y, SliderVerificationCodeType.getByValue(type));
        return null;
    }
}
