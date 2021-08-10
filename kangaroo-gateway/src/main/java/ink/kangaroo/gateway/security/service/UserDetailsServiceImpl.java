package ink.kangaroo.gateway.security.service;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import ink.kangaroo.system.api.RemoteUserService;
import ink.kangaroo.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:22
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final RemoteUserService remoteUserService;

    @Autowired
    @Lazy
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, RemoteUserService remoteUserService) {
        this.passwordEncoder = passwordEncoder;
        this.remoteUserService = remoteUserService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(
                "user",
                passwordEncoder.encode("user"),
                true, true, true, true, new ArrayList<>(),
                1L
        );
        R<LoginUser> userInfo = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (R.isOk(userInfo.getCode())) {

        }

        return Mono.just(securityUserDetails);
    }
}
