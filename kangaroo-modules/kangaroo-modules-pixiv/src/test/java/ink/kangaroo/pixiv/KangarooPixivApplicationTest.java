package ink.kangaroo.pixiv;

import com.alibaba.fastjson.JSON;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import ink.kangaroo.common.ekuaishou.model.adunit.param.GetAdUnitInfoParam;
import ink.kangaroo.common.ekuaishou.model.adunit.result.AdUnitDetailsDataResult;
import ink.kangaroo.common.ekuaishou.model.base.BaseResult;
import ink.kangaroo.common.pixiv.config.PixivClient;
import ink.kangaroo.common.pixiv.model.rank.PixivRankContent;
import ink.kangaroo.common.pixiv.model.rank.PixivRankMode;
import ink.kangaroo.common.pixiv.model.rank.param.GetPixivRankParam;
import ink.kangaroo.common.pixiv.model.rank.result.PixivRankResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/15 21:59
 */
@SpringBootTest
@Rollback(value = false)
@Transactional
public class KangarooPixivApplicationTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PixivClient pixivClient;

    public static void main(String[] args) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Token", "0cbf8c7f6161d06dab75cbcefb669387");
        httpHeaders.add("Content-Type", "application/json");
        Map<String, String> param = new HashMap<>();
        param.put("advertiser_id", "10594806");
        GetAdUnitInfoParam getAdUnitInfoParam = new GetAdUnitInfoParam();
//        getAdUnitInfoParam.setAdvertiser_id(10594806);
        getAdUnitInfoParam.setAdvertiserId(10594806);
        getAdUnitInfoParam.setPage(1);
        getAdUnitInfoParam.setPageSize(1);
        String s = JSON.toJSONString(getAdUnitInfoParam);
        System.out.println(s);
        ResponseEntity<BaseResult> stringResponseEntity = RestTemplateUtils.post("https://ad.e.kuaishou.com/rest/openapi/v1/ad_unit/list", httpHeaders, s, BaseResult.class);
        BaseResult<AdUnitDetailsDataResult> baseResult = new BaseResult<>();
        BeanUtils.copyProperties(stringResponseEntity.getBody(), baseResult);
        System.out.println(baseResult);

//        ResponseEntity<AdvertiserAccountInfoResult> advertiserAccountInfoResultResponseEntity = RestTemplateUtils.get("https://ad.e.kuaishou.com/rest/openapi/v1/advertiser/info", httpHeaders, AdvertiserAccountInfoResult.class, param);
//        System.out.println(advertiserAccountInfoResultResponseEntity);
////        String baseUrl = "http://192.168.1.107:91";
//        String baseUrl = "https://ty.xjcod.com";
//        Map<String, String> map = new HashMap<>();
//        //获取定向人群 api
////        map.put(baseUrl + "/api/mgr/tencent/create/target", "{\"accountId\":16961308,\"targetingId\":3188569276}");
//        //获取定向人气列表 api
//        map.put(baseUrl + "/api/mgr/tencent/create/targets", "{\"accountId\":16961308,\"targetingName\":\"\"}");
//        //获取跳转页 api
//        map.put(baseUrl + "/api/mgr/tencent/create/profiles", "{\"accountId\":16961308}");
//        //获取商品库列表 api
//        map.put(baseUrl + "/api/mgr/tencent/create/productCatalogs", "[{\"accountId\":16961308}]");
//        //行动按钮列表
//        //map.put(baseUrl + "/api/mgr/tencent/create/actionBtnTexts", "true");
//        //品牌形象列表 api
//        map.put(baseUrl + "/api/mgr/tencent/create/brands", "{\"accountId\":16961308}");
//        //获取腾讯的动态词包
////        map.put(baseUrl + "/api/mgr/tencent/create/trendsWords", "{}");
//        //获取商品库里商品列表 api
//        map.put(baseUrl + "/api/mgr/tencent/create/products", "{\"accountId\":16961308,\"productCatalogId\":268724245,\"productName\":\"\"}");
//        ExecutorService exec = Executors.newFixedThreadPool(map.keySet().size());
//        for (int i = 0; i < 2; i++) {
//            for (String url : map.keySet()) {
//                Runnable task = () -> {
//                    extracted(url, map.get(url));
//                };
//                exec.submit(task);
//            }
//        }
//
//        exec.shutdown();
//        while (true) {
//            if (exec.isTerminated()) {
//                System.out.println("所有的子线程都结束了！");
//                break;
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//        }
    }

    @Test
    void contextLoad() {
        GetPixivRankParam getPixivRankParam = new GetPixivRankParam();
        getPixivRankParam.setMode(PixivRankMode.PIXIV_RANK_DAILY);
        getPixivRankParam.setContent(PixivRankContent.PIXIV_RANK_ALL);
        getPixivRankParam.setPageNum(1);
        getPixivRankParam.setDate("20211021");
        PixivRankResult pixivRank = pixivClient.getPixivRank(getPixivRankParam);
        System.out.println(pixivRank);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        Map<String, String> param = new HashMap<>();
//        param.put("access_token", "4ef5e661-08cd-4358-862f-d0e5671d1e06");
//        param.put("advertiser_id", "5823815");
//        ResponseEntity<String> stringResponseEntity = RestTemplateUtils.get("https://ad.e.kuaishou.com/rest/openapi/v1/advertiser/info", httpHeaders, String.class, param);
//        System.out.println(stringResponseEntity);
//
//        ResponseEntity<AdvertiserAccountInfoResult> advertiserAccountInfoResultResponseEntity = RestTemplateUtils.get("https://ad.e.kuaishou.com/rest/openapi/v1/advertiser/info", httpHeaders, AdvertiserAccountInfoResult.class, param);
//        System.out.println(advertiserAccountInfoResultResponseEntity);
//        int currentHours = 15;
//        int currentMinute = 5;
//
//        List<CopyTask> copyTask = mongoTemplate.find(Query.query(Criteria.where("is_timer_create").is(true).and("is_timing_enable").is(true).and("is_deleted").is(false).and("timer_time").is(String.format("%02d",currentHours)+":"+String.format("%02d",currentMinute))), CopyTask.class);
//        System.out.println(String.format("%02d",currentHours)+":"+String.format("%02d",currentMinute));
//        System.out.println(copyTask);
//        Temporary temporary = new Temporary();
//        temporary.setState(0);
//        mongoTemplate.insert(temporary);
//        Query query = Query.query(Criteria.where("task_id").is("6139c453d6063b000134b4a9"));
//        query.skip(0);
//        query.limit(10);
//        query.with(Sort.by(
//                Sort.Order.desc("create_time")
//        ));
//        List<TimingCreateHistory> timingCreateHistories = mongoTemplate.find(query, TimingCreateHistory.class);
//        System.out.println(timingCreateHistories);
//        for (TimingCreateHistory timingCreateHistory : timingCreateHistories) {
//            System.out.println(timingCreateHistory.getCreateTime());
//        }
//        target();
//        targets();
//        profiles();
//        productCatalogs();
//        actionBtnTexts();
//        brands();
//        trendsWords();
//        products();
    }

    void target() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/target";
        String param = "{\"accountId\":16961308,\"targetingId\":3188569276}";
        extracted(url, param);
    }

    void targets() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/targets";
        String param = "{\"accountId\":16961308,\"targetingName\":\"\"}";
        extracted(url, param);
    }

    void profiles() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/profiles";
        String param = "{\"accountId\":16961308}";
        extracted(url, param);
    }

    void productCatalogs() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/productCatalogs";
        String param = "[{\"accountId\":16961308}]";
        extracted(url, param);
    }

    void actionBtnTexts() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/actionBtnTexts";
        String param = "true";
        extracted(url, param);
    }

    void brands() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/brands";
        String param = "{\"accountId\":16961308}";
        extracted(url, param);
    }

    void trendsWords() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/trendsWords";
        String param = "{}";
        extracted(url, param);
    }

    void products() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/products";
        String param = "{\"accountId\":16961308,\"productCatalogId\":268724245,\"productName\":\"\"}";
        extracted(url, param);
    }


    private static void extracted(String url, String param) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        // 添加额外http请求头参数 token
        headers.add("token", "1440947183:380c4d2c167c4d5c82b88515faaede1da566a0a4552ca9639e5f8b08f669aed2ed091333Yh6Ud");
        HttpEntity<String> request = new HttpEntity<>(param, headers);
        while (true) {
            ResponseEntity<String> stringResponseEntity = RestTemplateUtils.post(url, request, String.class);
//            System.out.println(stringResponseEntity);
            System.out.println(url.substring(url.lastIndexOf("/")) + ":\t" + stringResponseEntity.getBody());
        }
    }
}
