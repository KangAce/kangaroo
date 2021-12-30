package ink.kangaroo.system.api;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.constant.ServiceNameConstants;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.system.api.factory.RemoteConfigFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 用户服务
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 0:18
 */
@FeignClient(contextId = "remoteConfigService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService {

    /**
     * 通过用户名查询用户信息
     *
     * @param configKey 用户名
     * @param source    请求来源
     * @return 结果
     */
    @GetMapping(value = "/config/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable("configKey") String configKey, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
