package ink.kangaroo.pixiv.api.factory;

import ink.kangaroo.pixiv.api.RemotePixivService;
import ink.kangaroo.common.core.domain.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/13 13:44
 */
@Component
public class RemotePixivFallbackFactory implements FallbackFactory<RemotePixivService> {
    @Override
    public RemotePixivService create(Throwable cause) {
        return new RemotePixivService() {

            @Override
            public R<?> pullPixivRank(String source) {
                return null;
            }
        };
    }
}
