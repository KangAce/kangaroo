package ink.kangaroo.gateway.security.service;

import ink.kangaroo.common.core.constant.Constants;
import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.ServletUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.core.utils.ip.IpUtils;
import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import ink.kangaroo.system.api.RemoteLogService;
import ink.kangaroo.system.api.RemoteUserService;
import ink.kangaroo.system.api.domain.SysLogininfor;
import ink.kangaroo.system.api.domain.SysUser;
import ink.kangaroo.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:22
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final RemoteUserService remoteUserService;
    private final RemoteLogService remoteLogService;

    @Autowired
    @Lazy
    public UserDetailsServiceImpl(RemoteUserService remoteUserService, RemoteLogService remoteLogService) {
        this.remoteUserService = remoteUserService;
        this.remoteLogService = remoteLogService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        R<LoginUser> userInfoR = remoteUserService.getUserInfo(username, SecurityConstants.INNER);
        SecurityUserDetails securityUserDetails = null;
        if (R.isOk(userInfoR.getCode())) {
            LoginUser loginUser = userInfoR.getData();
            SysUser sysUser = loginUser.getSysUser();
            securityUserDetails = new SecurityUserDetails(
                    sysUser.getUserName(),
                    sysUser.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    new ArrayList<>(),
                    sysUser.getUserId()
            );
            Collection<GrantedAuthority> authorities = securityUserDetails.getAuthorities();
            Set<String> permissions = loginUser.getPermissions();
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
            securityUserDetails.setAuthorities(authorities);
        }

        assert securityUserDetails != null;
        return Mono.just(securityUserDetails);
    }

}
