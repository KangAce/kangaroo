package ink.kangaroo.system.api.factory;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.system.api.RemoteConfigService;
import ink.kangaroo.system.api.RemoteUserService;
import ink.kangaroo.system.api.domain.SysUser;
import ink.kangaroo.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:26
 */
@Component
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteConfigService create(Throwable throwable) {
        log.error("配置服务调用失败:{}", throwable.getMessage());
        return new RemoteConfigService() {
            @Override
            public AjaxResult getConfigKey(String configKey, String source) {
                return AjaxResult.fail("获取配置失败:" + throwable.getMessage());
            }
        };
    }
}