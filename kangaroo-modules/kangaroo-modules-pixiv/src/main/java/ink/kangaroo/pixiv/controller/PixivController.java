package ink.kangaroo.pixiv.controller;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.pixiv.config.PixivClient;
import ink.kangaroo.common.redis.lock.annotation.CacheLock;
import ink.kangaroo.common.redis.lock.annotation.CacheParam;
import ink.kangaroo.common.security.annotation.InnerAuth;
import ink.kangaroo.pixiv.model.entity.PixivArtist;
import ink.kangaroo.pixiv.model.entity.artwords.ImageUrl;
import ink.kangaroo.pixiv.model.vo.PixivArtwordVo;
import ink.kangaroo.pixiv.service.PixivRankService;
import ink.kangaroo.pixiv.service.PixivService;
import ink.kangaroo.pixiv.service.impl.PixivArtistService;
import ink.kangaroo.system.api.RemoteConfigService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/12 23:55
 */
@RestController
@RequestMapping("/api/pixiv")
public class PixivController extends BaseController {

    private final PixivClient pixivClient;
    private final RemoteConfigService remoteConfigService;
    private final PixivRankService pixivRankService;
    private final PixivService pixivService;
    private final PixivArtistService pixivArtistService;

    public PixivController(PixivClient pixivClient, RemoteConfigService remoteConfigService, PixivRankService pixivRankService, PixivService pixivService, PixivArtistService pixivArtistService) {
        this.pixivClient = pixivClient;
        this.remoteConfigService = remoteConfigService;
        this.pixivRankService = pixivRankService;
        this.pixivService = pixivService;
        this.pixivArtistService = pixivArtistService;
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
        return AjaxResult.success(userId + ":" + username);
    }

    @GetMapping("/wp")
//    @StaticApiCaChe
//    @CacheLock(prefix = "wp")
    public Page<PixivArtwordVo> wpPage(HttpServletRequest request, @PageableDefault(sort = "rankNumber", direction = ASC) @CacheParam Pageable pageable, @CacheParam String date, @NotNull @CacheParam String mode, Integer flag) {
        if (StringUtils.isBlank(date)) {
            date = DateUtils.parseDateToStr(DateUtils.YYYYMMDD, DateUtils.addMinutes(DateUtils.getNowDate(), -(35 * 60 + 25)));
        }
        Page<PixivArtwordVo> list = pixivService.list(date, mode, flag, pageable);
        return list;
    }

    @GetMapping("/img/{type}/{id}/{order}/{clarity}")
    public void img(@PathVariable(value = "type") String type, @PathVariable(value = "id") String id, @PathVariable(value = "order") Integer order, HttpServletResponse response, @PathVariable(value = "clarity") String clarity) {

        String thumbMini = "";

        if ("artist".equals(type)) {
            //使用Long 主键
            PixivArtist byId = pixivArtistService.getById(Long.valueOf(id));
            thumbMini = byId.getAvatar();
        } else if ("artwork".equals(type)) {
//            使用String PixivId
            if (order != null && order >= 0) {
                ImageUrl imageUrl = pixivRankService.getIllustPage(id).get(order).getImageUrl();
                switch (clarity) {
                    case "thumb":
                        thumbMini = imageUrl.getThumbMini();
                        break;
                    case "smail":
                        thumbMini = imageUrl.getSmall();
                        break;
                    case "regular":
                        thumbMini = imageUrl.getRegular();
                        break;
                    case "original":
                        thumbMini = imageUrl.getOriginal();
                        break;
                    default:
                        thumbMini = imageUrl.getSmall();
                }

                String imageType = FilenameUtils.getExtension(thumbMini);
                System.out.println(imageType);
            } else {
            }
        } else {
        }
        if ("png".equals(FilenameUtils.getExtension(thumbMini))) {
            response.setContentType("image/png");
        } else {
            response.setContentType("image/jpeg");
        }
        //设置文件名称
//        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

        InputStream it = pixivClient.getInputStream(thumbMini);
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
            to(it, os, remoteConfigService.getConfigKey("default:download:size", SecurityConstants.INNER).getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void to(InputStream it, OutputStream os, Object size) {
        try {
            /**
             * 加个限速，在下载原图的时候加上进入条，增加一个下载等待时间；
             */
            //文件拷贝
            byte[] flush = new byte[1024];
            int len = 0;

            while (0 <= (len = it.read(flush))) {
                os.write(flush, 0, len);
//                Thread.sleep(500);
            }
           /* while (true) {
                assert it != null;
                if (!(0 <= (len = it.read(flush)))) break;
                os.write(flush, 0, len);
                Thread.sleep(500);
            }*/
            os.flush();
            //关闭流的注意 先打开的后关
            os.close();
            it.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
