package ink.kangaroo.gateway.security.service;

import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:22
 */
public class UserDetailsServiceImpl  implements ReactiveUserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(
                "user",
                passwordEncoder.encode("user"),
                true, true, true, true, new ArrayList<>(),
                1L
        );
        return Mono.just(securityUserDetails);
    }
}
