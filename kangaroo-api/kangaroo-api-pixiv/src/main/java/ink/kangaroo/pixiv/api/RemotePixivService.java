package ink.kangaroo.pixiv.api;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.pixiv.api.factory.RemotePixivFallbackFactory;
import ink.kangaroo.common.core.constant.ServiceNameConstants;
import ink.kangaroo.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/13 13:42
 */
@FeignClient(contextId = "remoteMailService", value = ServiceNameConstants.PIXIV_SERVICE, fallbackFactory = RemotePixivFallbackFactory.class)
public interface RemotePixivService {

    /**
     * 抓取pixiv排行榜
     * @return
     */
    @GetMapping("/api/pixiv/pullAllRank")
    public R<?> pullPixivRank(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
