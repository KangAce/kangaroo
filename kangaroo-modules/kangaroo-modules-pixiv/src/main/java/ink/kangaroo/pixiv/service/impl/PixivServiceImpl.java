package ink.kangaroo.pixiv.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.redis.service.RedisService;
import ink.kangaroo.pixiv.model.entity.PixivArtword;
import ink.kangaroo.pixiv.model.entity.PixivLike;
import ink.kangaroo.pixiv.model.entity.PixivRank;
import ink.kangaroo.pixiv.model.entity.PixivRankArtword;
import ink.kangaroo.pixiv.model.vo.PixivArtwordDetailVo;
import ink.kangaroo.pixiv.model.vo.PixivArtwordVo;
import ink.kangaroo.pixiv.model.vo.PixivImageUrlVo;
import ink.kangaroo.pixiv.repository.pixiv.*;
import ink.kangaroo.pixiv.service.PixivService;
import ink.kangaroo.pixiv.utils.RequestUtil;
import ink.kangaroo.system.api.RemoteConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * https://www.pixiv.net/ajax/top/manga?mode=all&lang=zh 推荐作品
 * https://www.pixiv.net/ajax/top/illust?mode=all&lang=zh
 */
@Slf4j
@Service
@AllArgsConstructor
public class PixivServiceImpl implements PixivService {

    private final ArtworkRepository artworkRepository;
    private final PixivRankRepository pixivRankRepository;
    private final PixivArtwordRepository pixivArtwordRepository;
    private final PixivRankArtwordRepository pixivRankArtwordRepository;
    private final RemoteConfigService remoteConfigService;
    private final RedisService redisService;
    private final RequestUtil requestUtil;
    private final PixivLikeRepository pixivLikeRepository;


    /**
     * @param date
     * @param mode
     * @param pageable
     * @return
     */
    @Override
    public Page<PixivArtwordVo> list(String date, String mode, Integer flag, Pageable pageable) {
        //屏蔽的作者pid
        List<String> userIds = new ArrayList<>();
        //屏蔽的作品id
        List<String> artworkIds = new ArrayList<>();
        String userIdStr = redisService.getCacheObject("pixiv:shield:by:user:ids");
        String artworkIdStr = redisService.getCacheObject("pixiv:shield:by:artwork:ids");
        if (StringUtils.isNotBlank(artworkIdStr)) {
            JSONArray objects = JSON.parseArray(artworkIdStr);
            artworkIds = objects.stream().map(Object::toString).collect(Collectors.toList());
        } else {
            AjaxResult pixivShieldByUserId = remoteConfigService.getConfigKey("pixiv:shield:by:user:id", SecurityConstants.INNER);
            if (pixivShieldByUserId.isSuccess() && pixivShieldByUserId.getData() instanceof Long) {
                List<PixivLike> allByUserId = pixivLikeRepository.findAllByUserId((Long) pixivShieldByUserId.getData());
                if (!CollectionUtils.isEmpty(allByUserId)) {
                    List<Long> collect = allByUserId.stream().map(PixivLike::getArtworkId).collect(Collectors.toList());
                    List<PixivArtword> allByIdIn = pixivArtwordRepository.findAllByIdIn(collect);
                    artworkIds = allByIdIn.stream().map(PixivArtword::getIllustId).collect(Collectors.toList());
                    //将查询好的内容转为json塞入缓存
//                cacheStore.put("pixiv_shield_by_artwork_ids",JSON.toJSONString(artworkIds),5,TimeUnit.MINUTES);
                }
            }

        }
        if (StringUtils.isNotBlank(userIdStr)) {
            JSONArray objects = JSON.parseArray(userIdStr);
            userIds = objects.stream().map(Object::toString).collect(Collectors.toList());
        } else {

        }

        //因为page的tolist会将对象转换成Collection<> 并且不再支持addAll方法
        List<Long> attachment = new ArrayList<>();
        PixivRank pixivRankByModeAndDate = pixivRankRepository.findPixivRankByModeAndDateAndContent(mode, date, "all");
//        Page<PixivRankArtword> rankArtwords = pixivRankArtwordRepository.findByPixivRankArtwordKey_Rank(pixivRankByModeAndDate, pageable);
//        Page<PixivRankArtword> rankArtwords = pixivRankArtwordRepository.findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThan(pixivRankByModeAndDate, 3, pageable);
        Page<PixivRankArtword> rankArtwords = null;
        if (!CollectionUtils.isEmpty(artworkIds)) {
            rankArtwords = pixivRankArtwordRepository.findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_IllustIdNotInAndPixivRankArtwordKey_Artword_SanityLevelLessThan(pixivRankByModeAndDate, 3, artworkIds, 4, pageable);

        } else {
            rankArtwords = pixivRankArtwordRepository.findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_SanityLevelLessThan(pixivRankByModeAndDate, 3, 4, pageable);

        }
//        Page<PixivRankArtword> rankArtwords = pixivRankArtwordRepository.findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_IllustIdNotIn(pixivRankByModeAndDate, 3,artworkIds, pageable);
        //加入插画缩略图附件
        attachment.addAll(rankArtwords.map(PixivRankArtword::getPixivArtword).map(PixivArtword::getThumbnailLocal).toList());
        //加入用户头像缩略图附件
        attachment.addAll(rankArtwords.map(e -> e.getPixivArtword().getArtist().getAttachmentId()).filter(Objects::nonNull).toList());
//        List<Attachment> attachments = attachmentService.listByIdsWithInTime(attachment, -29L, TimeUnit.DAYS);
//        Map<Long, Attachment> attachmentMap = attachments.stream().collect(Collectors.toMap(Attachment::getId, e -> e, (e1, e2) -> e1));
        Page<PixivArtword> pixivArtwordPage = rankArtwords.map(PixivRankArtword::getPixivArtword).map(e -> {
//            if (flag != 2) {
//                if (e.getThumbnailLocal() == null || attachmentMap.get(e.getThumbnailLocal()) == null) {
//                    // 文件名
//                    String filePathUrl = e.getThumbnail();
//                    //开始获取链接
//                    Attachment upload = getAttachment(filePathUrl);
//                    e.setThumbnailLocal(upload.getId());
//                    attachmentMap.put(upload.getId(), upload);
//                }
//                if (e.getArtist().getAttachmentId() == null || attachmentMap.get(e.getArtist().getAttachmentId()) == null) {
//                    String filePathUrl = e.getArtist().getAvatar();
//                    Attachment upload = getAttachment(filePathUrl);
//                    System.out.println(upload + ":" + filePathUrl);
//                    e.getArtist().setAttachmentId(upload.getId());
//                    attachmentMap.put(upload.getId(), upload);
//                }
//            }
            return e;
        });
        pixivArtwordRepository.saveAll(pixivArtwordPage.toList());
        return pixivArtwordPage.map(e -> {
            PixivArtwordVo pixivArtwordVo = new PixivArtwordVo(e);
            if (flag == 0) {
//                pixivArtwordVo.setImgUrl(attachmentMap.get(e.getThumbnailLocal()).getPath());
//                pixivArtwordVo.setAvatar(attachmentMap.get(e.getArtist().getAttachmentId()).getPath());

            } else if (flag == 1) {
//                pixivArtwordVo.setImgUrl(attachmentMap.get(e.getThumbnailLocal()).getThumbPath());
//                pixivArtwordVo.setAvatar(attachmentMap.get(e.getArtist().getAttachmentId()).getThumbPath());
            } else if (flag == 2) {
                AjaxResult webBaseUrl = remoteConfigService.getConfigKey("web.base.url", SecurityConstants.INNER);
                pixivArtwordVo.setImgUrl(webBaseUrl.getMessage() + "/api/pixiv/img/artwork/" + e.getIllustId() + "/0/smail");
                pixivArtwordVo.setAvatar(webBaseUrl.getMessage() + "/api/pixiv/img/artist/" + e.getArtist().getId() + "/0/smail");
            }
            return pixivArtwordVo;
        });
    }

    @Override
    public PixivArtwordDetailVo detail(Long userId, Long id) {
        PixivArtword one = pixivArtwordRepository.getOne(id);
        PixivArtwordDetailVo pixivArtwordDetailVo = new PixivArtwordDetailVo(one);
        AjaxResult webBaseUrl = remoteConfigService.getConfigKey("web.base.url", SecurityConstants.INNER);
        pixivArtwordDetailVo.setAvatar(webBaseUrl.getMessage() + "/api/pixiv/img/artist/" + one.getArtist().getId() + "/0/smail");
        List<PixivImageUrlVo> imgList = new ArrayList<>();
        for (int i = 0; i < one.getPageCount(); i++) {
            PixivImageUrlVo pixivImageUrlVo = new PixivImageUrlVo();
            /*pixivImageUrlVo.setImageUrl(optionService.getWebBaseUrl()+"/api/pixiv/img/artwork/"+one.getIllustId()+"/"+i+"/thumb");
            imgList.add(pixivImageUrlVo);
            pixivImageUrlVo = new PixivImageUrlVo();
            pixivImageUrlVo.setImageUrl(optionService.getWebBaseUrl()+"/api/pixiv/img/artwork/"+one.getIllustId()+"/"+i+"/smail");
            imgList.add(pixivImageUrlVo);*/
//            pixivImageUrlVo = new PixivImageUrlVo();
            pixivImageUrlVo.setImageUrl(webBaseUrl.getMessage() + "/api/pixiv/img/artwork/" + one.getIllustId() + "/" + i + "/regular");
            imgList.add(pixivImageUrlVo);
            /*pixivImageUrlVo = new PixivImageUrlVo();
            pixivImageUrlVo.setImageUrl(optionService.getWebBaseUrl()+"/api/pixiv/img/artwork/"+one.getIllustId()+"/"+i+"/original");
            imgList.add(pixivImageUrlVo);*/
        }
        pixivArtwordDetailVo.setImgList(imgList);
        pixivArtwordDetailVo.setLiked(pixivLikeRepository.findAllByUserIdAndArtworkId(userId, id) != null);
        ;
        return pixivArtwordDetailVo;
    }

    @Override
    public boolean like(Long userId, Long id) {
        PixivLike allByUserIdAndArtworkId = pixivLikeRepository.findAllByUserIdAndArtworkId(userId, id);
        if (allByUserIdAndArtworkId != null) {
            pixivLikeRepository.delete(allByUserIdAndArtworkId);
            return false;
        } else {
//        PixivArtword byIllustId = pixivArtwordRepository.findByIllustId(id);
            PixivLike pixivLike = new PixivLike();
            pixivLike.setArtworkId(id);
            pixivLike.setUserId(userId);
            pixivLikeRepository.save(pixivLike);
            return true;
        }
    }

    public Page<PixivArtwordVo> listByLikeUserId(Long userId, Pageable pageable) {

        AjaxResult webBaseUrl = remoteConfigService.getConfigKey("web.base.url", SecurityConstants.INNER);
        List<PixivLike> byUserId = pixivLikeRepository.findAllByUserId(userId);
        List<Long> collect = byUserId.stream().map(PixivLike::getArtworkId).collect(Collectors.toList());
        Page<PixivArtword> allByIdIn = pixivArtwordRepository.findAllByIdIn(collect, pageable);
        Page<PixivArtwordVo> map = allByIdIn.map(e -> {
            PixivArtwordVo pixivArtwordVo = new PixivArtwordVo(e);
            pixivArtwordVo.setImgUrl(webBaseUrl.getMessage() + "/api/pixiv/img/artwork/" + e.getIllustId() + "/" + 0 + "/smail");

            pixivArtwordVo.setAvatar(webBaseUrl.getMessage() + "/api/pixiv/img/artist/" + e.getArtist().getId() + "/0/smail");
            return pixivArtwordVo;
        });
        return map;
    }


}
