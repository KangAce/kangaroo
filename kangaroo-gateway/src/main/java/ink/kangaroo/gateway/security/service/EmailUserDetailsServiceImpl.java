//package ink.kangaroo.gateway.security.service;
//
//import ink.kangaroo.common.core.constant.SecurityConstants;
//import ink.kangaroo.common.core.domain.R;
//import ink.kangaroo.common.core.utils.DecimalUtils;
//import ink.kangaroo.common.log.annotation.Log;
//import ink.kangaroo.common.log.enums.BusinessType;
//import ink.kangaroo.common.log.enums.OperatorType;
//import ink.kangaroo.gateway.security.domain.SecurityUserDetails;
//import ink.kangaroo.system.api.RemoteUserService;
//import ink.kangaroo.system.api.domain.SysUser;
//import ink.kangaroo.system.api.model.LoginUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Set;
//import java.util.concurrent.Executor;
//import java.util.concurrent.FutureTask;
//
///**
// * 在这里实现根据邮件或者手机号获取对应的验证码作为密码进行验证
// *
// * @author kbw
// * @version 1.0
// * @date 2021/8/9 17:22
// */
//@Service
//public class EmailUserDetailsServiceImpl implements ReactiveUserDetailsService {
//
//    private final RemoteUserService remoteUserService;
//    private final Executor executor;
//
//    @Lazy
//    @Autowired(required = false)
//    public EmailUserDetailsServiceImpl(RemoteUserService remoteUserService, @Qualifier("customizeThreadPool") Executor executor) {
//        this.remoteUserService = remoteUserService;
//        this.executor = executor;
//    }
//
//    @Log(title = "gateway", businessType = BusinessType.GRANT, operatorType = OperatorType.MANAGE, isSaveRequestData = true)
//    @Override
//    public Mono<UserDetails> findByUsername(String username) {
////        String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456");
//        //
//        R<LoginUser> userInfoR = loginUserR(username);
//        SecurityUserDetails securityUserDetails = null;
//        if (userInfoR != null && R.isOk(userInfoR.getCode())) {
//            LoginUser loginUser = userInfoR.getData();
//            SysUser sysUser = loginUser.getSysUser();
//            securityUserDetails = new SecurityUserDetails(
//                    sysUser.getUserName(),
//                    sysUser.getPassword(),
//                    true,
//                    true,
//                    true,
//                    true,
//                    new ArrayList<>(),
//                    sysUser.getUserId()
//            );
//            Collection<GrantedAuthority> authorities = securityUserDetails.getAuthorities();
//            if (CollectionUtils.isEmpty(authorities)) {
//                authorities = new ArrayList<>();
//            }
//            Set<String> permissions = loginUser.getPermissions();
//            if (!CollectionUtils.isEmpty(permissions)) {
//                for (String permission : permissions) {
//                    authorities.add(new SimpleGrantedAuthority(permission));
//                    securityUserDetails.setAuthorities(authorities);
//                }
//            }
//            return Mono.just(securityUserDetails);
//        } else {
//            throw new UsernameNotFoundException("");
//        }
//
////        assert securityUserDetails != null;
////        return Mono.just(securityUserDetails);
//    }
//
//    R<LoginUser> loginUserR(String username) {
//        //因为Callable接口是函数式接口，可以使用Lambda表达式
//        FutureTask<? extends R<LoginUser>> task = new FutureTask<>(() -> remoteUserService.getUserInfo(username, SecurityConstants.INNER + ":" + DecimalUtils._10_to_N(System.currentTimeMillis(), 62)));
//        executor.execute(task);
//        try {
//            return task.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
