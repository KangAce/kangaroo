package ink.kangaroo.pixiv.controller;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.security.annotation.InnerAuth;
//import ink.kangaroo.pixiv.service.PixivRankService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PixivController {

//    @Autowired
//    PixivRankService pixivRankService;

    @PostMapping("pullAllRank")
    @Operation(summary = "Tests the SMTP service")
    @InnerAuth
    public R<Object> pullAllRank() {
//        pixivRankService.pullAllRank();
        return R.ok();
    }
}
