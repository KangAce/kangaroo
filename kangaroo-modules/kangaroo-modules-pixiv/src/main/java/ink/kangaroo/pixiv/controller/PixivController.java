package ink.kangaroo.pixiv.controller;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.security.annotation.InnerAuth;
//import ink.kangaroo.pixiv.service.PixivRankService;
import ink.kangaroo.pixiv.model.vo.PixivArtwordVo;
import ink.kangaroo.pixiv.service.PixivRankService;
import ink.kangaroo.pixiv.service.PixivService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/12 23:55
 */
@RestController
@RequestMapping("/api/pixiv")
public class PixivController extends BaseController {

    @Autowired
    PixivRankService pixivRankService;
    private final PixivService pixivService;
    @PostMapping("pullAllRank")
    @Operation(summary = "Tests the SMTP service")
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
        return AjaxResult.success(userId+":"+username);
    }

    @GetMapping("/wp")
//    @StaticApiCaChe
    public Page<PixivArtwordVo> wpPage(HttpServletRequest request, @PageableDefault(sort = "rankNumber", direction = ASC) @CacheParam Pageable pageable, @CacheParam String date, @NotNull @CacheParam String mode, Integer flag) {
        if (StringUtils.isBlank(date)) {
            date = DateUtils.parseDateToStr(DateUtils.YYYYMMDD,DateUtils.addMinutes(DateUtils.getNowDate(),-(35 * 60 + 25)));
        }
        Page<PixivArtwordVo> list = pixivService.list(date, mode, flag, pageable);
        return list;
    }
}
