package ink.kangaroo.pixiv.controller;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.security.annotation.InnerAuth;
import ink.kangaroo.pixiv.service.PixivRankService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/12 23:55
 */
@RestController
@RequestMapping("/api/pixiv")
public class PixivController extends BaseController {

    private final PixivRankService pixivRankService;
//    private final PixivService pixivService;

    public PixivController(PixivRankService pixivRankService/*, PixivService pixivService*/) {
        this.pixivRankService = pixivRankService;
//        this.pixivService = pixivService;
    }

    @GetMapping("pullAllRank")
    @Operation(summary = "爬Pixiv任务接口")
    @InnerAuth
    public R<Object> pullAllRank() {
        pixivRankService.pullAllRank();
        return R.ok();
    }

    @GetMapping("test")
    public AjaxResult pixivTest() {
        Long userId = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
//        pixivRankService.pullAllRank();
        return AjaxResult.success(userId + ":" + username);
    }

//    @GetMapping("/wp")
////    @StaticApiCaChe
//    @CacheLock(prefix = "wp")
//    public Page<PixivArtwordVo> wpPage(HttpServletRequest request, @PageableDefault(sort = "rankNumber", direction = ASC) @CacheParam Pageable pageable, @CacheParam String date, @NotNull @CacheParam String mode, Integer flag) {
//        if (StringUtils.isBlank(date)) {
//            date = DateUtils.parseDateToStr(DateUtils.YYYYMMDD, DateUtils.addMinutes(DateUtils.getNowDate(), -(35 * 60 + 25)));
//        }
//        Page<PixivArtwordVo> list = pixivService.list(date, mode, flag, pageable);
//        return list;
//    }
}
