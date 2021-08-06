package ink.kangaroo.common.security.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.utils.ServletUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.core.utils.ip.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/4 9:34
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (StringUtils.isNotNull(httpServletRequest)) {
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            // 传递用户信息请求头，防止丢失
            String userId = headers.get(SecurityConstants.DETAILS_USER_ID);
            if (StringUtils.isNotEmpty(userId)) {
                requestTemplate.header(SecurityConstants.DETAILS_USER_ID, userId);
            }
            String userName = headers.get(SecurityConstants.DETAILS_USERNAME);
            if (StringUtils.isNotEmpty(userName)) {
                requestTemplate.header(SecurityConstants.DETAILS_USERNAME, userName);
            }
            String authentication = headers.get(SecurityConstants.AUTHORIZATION_HEADER);
            if (StringUtils.isNotEmpty(authentication)) {
                requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER, authentication);
            }

            // 配置客户端IP
            requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr(ServletUtils.getRequest()));
        }
    }
}
