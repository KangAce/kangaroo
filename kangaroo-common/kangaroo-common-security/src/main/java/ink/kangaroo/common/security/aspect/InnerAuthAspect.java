package ink.kangaroo.common.security.aspect;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.exception.InnerAuthException;
import ink.kangaroo.common.core.utils.DecimalUtils;
import ink.kangaroo.common.core.utils.ServletUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.security.annotation.InnerAuth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 内部服务调用验证处理
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 1:07
 */
@Aspect
@Component
public class InnerAuthAspect implements Ordered {
    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint point, InnerAuth innerAuth) throws Throwable {
        String source = ServletUtils.getRequest().getHeader(SecurityConstants.FROM_SOURCE);
        // 内部请求验证
        if (source == null) {
            throw new InnerAuthException("没有内部访问权限，不允许访问.");
        }
        String replace = source.replace(SecurityConstants.INNER + ":", "");
        try {
            System.out.println(System.currentTimeMillis() - DecimalUtils.N_to_10(replace, 62));
            if (System.currentTimeMillis() - DecimalUtils.N_to_10(replace, 62) > 60 * 1000) {
                throw new InnerAuthException("没有内部访问权限，不允许访问;");
            }
        } catch (Exception e) {
            throw new InnerAuthException("没有内部访问权限，不允许访问。");
        }
//        if (!StringUtils.equals(SecurityConstants.INNER, source)) {
//            throw new InnerAuthException("没有内部访问权限，不允许访问");
//        }

        String userid = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USER_ID);
        String username = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USERNAME);
        // 用户信息验证
        if (innerAuth.isUser() && (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))) {
            throw new InnerAuthException("没有设置用户信息，不允许访问 ");
        }
        return point.proceed();
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
