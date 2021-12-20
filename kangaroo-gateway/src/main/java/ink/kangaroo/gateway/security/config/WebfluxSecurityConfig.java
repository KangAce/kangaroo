package ink.kangaroo.gateway.security.config;

import ink.kangaroo.gateway.security.config.properties.IgnoreWhiteProperties;
import ink.kangaroo.gateway.security.handler.*;
import ink.kangaroo.gateway.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 17:20
 */
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {
    //    @Autowired
//    private AuthenticationConverter authenticationConverter;
    @Autowired
    private DefaultAuthorizationManager defaultAuthorizationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Autowired
    private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Autowired
    private DefaultSecurityContextRepository defaultSecurityContextRepository;

    @Autowired
    private DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint;

    @Autowired
    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;

    /**
     * 自定义过滤权限
     */
    @Autowired
    IgnoreWhiteProperties ignoreWhiteProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        httpSecurity.authenticationManager().addFilterAfter()
        httpSecurity
//                .oauth2Login((oAuth2LoginSpec) -> {
//                    oAuth2LoginSpec
//                            //存储认证授权的相关信息 自定义JWT Token认证管理
//                            .securityContextRepository(defaultSecurityContextRepository)
//                            // 登录认证处理
//                            .authenticationManager(reactiveAuthenticationManager())
//                            //登录成功处理 自定义登录成功Handler
//                            .authenticationSuccessHandler(defaultAuthenticationSuccessHandler)
//                            // 登录失败处理 自定义登录失败Handler
//                            .authenticationFailureHandler(defaultAuthenticationFailureHandler)
//
//                    ;
//                })
                .formLogin((formLoginSpec) -> {
                    formLoginSpec
                            //存储认证授权的相关信息 自定义JWT Token认证管理
                            .securityContextRepository(defaultSecurityContextRepository)
                            // 登录认证处理
                            .authenticationManager(reactiveAuthenticationManager())
                            //登录成功处理 自定义登录成功Handler
                            .authenticationSuccessHandler(defaultAuthenticationSuccessHandler)
                            // 登录失败处理 自定义登录失败Handler
                            .authenticationFailureHandler(defaultAuthenticationFailureHandler)

                    ;
                })
                // 请求拦截处理
                .authorizeExchange(exchange -> {
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getPattern())) {
                                String[] pattern = ignoreWhiteProperties.getPattern().toArray(new String[0]);
                                exchange.pathMatchers(pattern).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getGet())) {
                                String[] get = ignoreWhiteProperties.getGet().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.GET, get).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getPost())) {
                                String[] post = ignoreWhiteProperties.getPost().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.POST, post).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getDelete())) {
                                String[] delete = ignoreWhiteProperties.getDelete().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.DELETE, delete).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getPut())) {
                                String[] put = ignoreWhiteProperties.getPut().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.PUT, put).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getHead())) {
                                String[] head = ignoreWhiteProperties.getHead().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.HEAD, head).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getPatch())) {
                                String[] patch = ignoreWhiteProperties.getPatch().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.PATCH, patch).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getOptions())) {
                                String[] options = ignoreWhiteProperties.getOptions().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.OPTIONS, options).permitAll();
                            }
                            if (!CollectionUtils.isEmpty(ignoreWhiteProperties.getTrace())) {
                                String[] trace = ignoreWhiteProperties.getTrace().toArray(new String[0]);
                                exchange.pathMatchers(HttpMethod.TRACE, trace).permitAll();
                            }
                            exchange.pathMatchers(HttpMethod.OPTIONS).permitAll();
                            exchange.anyExchange().access(defaultAuthorizationManager);
                        }
                )
                // 自定义处理
                .exceptionHandling((exceptionHandlingSpec -> {
                    exceptionHandlingSpec
                            // 未认证处理 自定义未认证Handler
                            .authenticationEntryPoint(defaultAuthenticationEntryPoint)
                            // 鉴权管理 自定义鉴权失败Handler
                            .accessDeniedHandler(defaultAccessDeniedHandler)
                            .and().csrf().disable();
                }));

        SecurityWebFilterChain chain = httpSecurity.build();
//        // 设置自定义登录参数转换器
//        chain.getWebFilters()
//                .filter(webFilter -> webFilter instanceof AuthenticationWebFilter)
//                .subscribe(webFilter -> {
//                    AuthenticationWebFilter filter = (AuthenticationWebFilter) webFilter;
//                    filter.setServerAuthenticationConverter(authenticationConverter);
//                });
        return chain;
    }

    /**
     * BCrypt密码编码
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> {
            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
            return Mono.empty();
        });

        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsServiceImpl));
        managers.add(tokenAuthenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }
}