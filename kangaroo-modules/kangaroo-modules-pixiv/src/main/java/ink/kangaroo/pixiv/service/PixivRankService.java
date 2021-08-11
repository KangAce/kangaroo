//package ink.kangaroo.pixiv.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ink.kangaroo.common.core.utils.DateUtils;
//import ink.kangaroo.pixiv.entity.PixivRank;
//import ink.kangaroo.pixiv.entity.PixivRankArtword;
//import ink.kangaroo.pixiv.entity.result.PixivRankContentResult;
//import ink.kangaroo.pixiv.entity.result.PixivRankResult;
//import ink.kangaroo.pixiv.utils.RequestUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//import java.util.stream.Collectors;
//
///**
// * @author kbw
// * @version 1.0
// * @date 2021/8/10 23:46
// */
//@Service
//@Slf4j
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class PixivRankService {
//    private final static String[] MODES = {"daily_all", "daily_illust", "weekly_all", "weekly_illust", "monthly_all", "monthly_illust", "rookie_illust", "original_all", "male_all", "female_all"};
//    private final ObjectMapper objectMapper;
//    private final RequestUtil requestUtil;
//
//
//    @CacheEvict(value = "rank", allEntries = true)
//    public void pullAllRank() {
//        String format = DateUtils.parseDateToStr(DateUtils.YYYYMMDD, DateUtils.addMinutes(DateUtils.getNowDate(), -(35 * 60 + 25)));
//        pullAllRank(format);
//    }
//
//    public void pullAllRank(String date) {
//        for (String mode : MODES) {
//            String content = "all";
//            String[] s = mode.split("_");
//            mode = s[0];
//            if (s.length > 1) {
//                content = s[1];
//            }
//            List<PixivRankArtword> rank = getArtwords(mode, content, date);
//            pixivRankArtwordRepository.saveAll(rank);
//            log.info(date + ":" + mode + " 排行爬取完毕");
//        }
//        log.info(date + "排行爬取完毕!");
//    }
//
//    private List<PixivRankArtword> getArtwords(String mode,String content, String date) {
//
//        ArrayList<PixivRankContentResult> illustrations = new ArrayList<>(100);
//        Integer i = 1;
//        boolean flag = true;
//        while (flag) {
//            try {
//                PixivRankResult artwords = getArtwords(mode,content, date, i);
//                illustrations.addAll(artwords.getContents());
//                flag = artwords.getNext() != null;
//                i = artwords.getNext();
////                break;
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        String rankMode;
//        switch (mode) {
//            case "female":
//                rankMode = "female";
//                break;
//            case "male":
//                rankMode = "male";
//                break;
//            default:
//                rankMode = mode;
//                break;
//        }
//        //查看排行榜是否存在，若没有则创建、若有则查询获取（应该是之前执行失败了才会出现已经有排行榜的可能）并保存新的排行榜
//        log.info("开始排行榜: " + mode + date);
////        PixivRank pixivRank = pixivRankRepository.findPixivRankByModeAndDate(mode, date);
//
//        PixivRank pixivRank = pixivRankRepository.findPixivRankByModeAndDateAndContent(mode, date,content);
//        if (pixivRank == null) {
//            pixivRank = new PixivRank(rankMode, date,content);
//            pixivRank = pixivRankRepository.save(pixivRank);
//        }
//        /**
//         * 获取已存在的插画
//         */
//        List<String> illustIds = illustrations.stream().map(PixivRankContentResult::getIllustId).collect(Collectors.toList());
//        List<PixivArtword> artwords = pixivArtwordRepository.findByIllustIdIn(illustIds);
//        Map<String, PixivArtword> existedArtwordMap = artwords.stream().collect(Collectors.toMap(PixivArtword::getIllustId, e -> e));
//        /**
//         * 获取已存在的作者
//         */
//        List<String> userIds = illustrations.stream().map(PixivRankContentResult::getUserId).collect(Collectors.toList());
//        List<PixivArtist> allByAccountIn = pixivArtistRepository.findAllByAccountIn(userIds);
//        Map<String, PixivArtist> existedArtistMap = allByAccountIn.stream().collect(Collectors.toMap(PixivArtist::getAccount, e -> e, (x1, x2) -> x1));
//        /**
//         * 获取已存在的标签
//         */
//        List<String> tagNameList = new ArrayList<>();
//        illustrations.forEach(e -> {
//            tagNameList.addAll(e.getTags());
//        });
//        //根据标签名称获取
//        List<PixivTag> byNameIn = pixivTagRepository.findByNameIn(tagNameList);
//        Map<String, PixivTag> existedTagsMap = byNameIn.stream().collect(Collectors.toMap(PixivTag::getName, e -> e, (x1, x2) -> x1));
//
//        PixivRank finalPixivRank = pixivRank;
//        return illustrations.stream().map(e -> {
////            log.info("开始插画: "+e.getIllustId());
//            //检查插画是否已经存在，如果存在则更新、关联排行版
//            PixivRankArtword pixivRankArtword = new PixivRankArtword();
//            PixivRankArtwordKey pixivRankArtwordKey = new PixivRankArtwordKey();
//            PixivArtword pixivArtword = existedArtwordMap.get(e.getIllustId());
//            if (pixivArtword == null) {
//                pixivArtword = new PixivArtword(e);
//                PixivArtist pixivArtist = existedArtistMap.get(e.getUserId());
//                if (pixivArtist == null) {
//                    pixivArtist = new PixivArtist(e);
//                    pixivArtistRepository.save(pixivArtist);
//                    /**
//                     * 同一排行榜有可能出现相同的作家。
//                     */
//                    pixivArtist = pixivArtistRepository.findByAccount(pixivArtist.getAccount());
//                    existedArtistMap.put(e.getUserId(), pixivArtist);
//                }
//                pixivArtword.setArtist(pixivArtist);
//                /**
//                 * 开始持久化标签逻辑
//                 */
//                List<PixivTag> pixivTags = new ArrayList<>();
//                //从DB根据姓名查询标签是否存在
//                e.getTags().forEach(v -> {
//                    PixivTag pixivTag = existedTagsMap.get(v);
//                    if (pixivTag == null) {
//                        pixivTag = new PixivTag();
//                        pixivTag.setName(v);
//                        PixivTag save = pixivTagRepository.save(pixivTag);
//                        pixivTag = pixivTagRepository.findById(save.getId()).get();
//                        existedTagsMap.put(v, pixivTag);
//
//                    }
//                    pixivTags.add(pixivTag);
//
//                });
//                //存在，添加到插画标签list中，暂时不开考虑未知的标签的插画list用不用添加插画
//                //不存在，创建并保存，重新查询一个新的tag放入map，将保存完成的标签放入插画的tagList
//                pixivArtword.setTags(pixivTags);
//                pixivArtword = pixivArtwordRepository.save(pixivArtword);
//            }
//            pixivRankArtwordKey.setArtword(pixivArtword);
//            pixivRankArtwordKey.setRank(finalPixivRank);
//            pixivRankArtword.setPixivRankArtwordKey(pixivRankArtwordKey);
//            pixivRankArtword.setRankNumber(e.getRank());
//            pixivRankArtword.setLastRanking(e.getYesRank());
//            //检查作者是否已经存在，如果存在则获取、更新，如果不存在则创建 ，最后关联
//            return pixivRankArtword;
//        }).collect(Collectors.toList());
//    }
//}
