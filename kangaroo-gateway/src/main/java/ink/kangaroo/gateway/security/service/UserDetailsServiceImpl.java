package ink.kangaroo.gateway.security.service;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.log.annotation.Log;
import ink.kangaroo.common.log.enums.BusinessType;
import ink.kangaroo.common.log.enums.OperatorType;
import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
import ink.kangaroo.system.api.RemoteUserService;
import ink.kangaroo.system.api.domain.SysUser;
import ink.kangaroo.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:22
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final RemoteUserService remoteUserService;

    @Lazy
    @Autowired(required = false)
    public UserDetailsServiceImpl(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    @Log(title = "gateway", businessType = BusinessType.GRANT, operatorType = OperatorType.MANAGE, isSaveRequestData = true)
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        //因为Callable接口是函数式接口，可以使用Lambda表达式
        FutureTask task = new FutureTask((Callable) () -> remoteUserService.getUserInfo(username, SecurityConstants.INNER));
        new Thread(task).start();
        R<LoginUser> userInfoR = null;
        try {
            userInfoR = (R<LoginUser>) task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (CollectionUtils.isEmpty(authorities)) {
                authorities = new ArrayList<>();
            }
            Set<String> permissions = loginUser.getPermissions();
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String permission : permissions) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                    securityUserDetails.setAuthorities(authorities);
                }
            }
            return Mono.just(securityUserDetails);
        } else {
            throw new UsernameNotFoundException("");
        }

//        assert securityUserDetails != null;
//        return Mono.just(securityUserDetails);
    }

}
