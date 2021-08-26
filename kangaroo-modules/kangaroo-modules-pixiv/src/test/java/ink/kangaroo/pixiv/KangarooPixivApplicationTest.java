package ink.kangaroo.pixiv;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/15 21:59
 */
@SpringBootTest
@Rollback(value = true)
@Transactional
public class KangarooPixivApplicationTest {

    public static void main(String[] args) {
//        String baseUrl = "http://192.168.1.107:91";
        String baseUrl = "https://ty.xjcod.com";
        Map<String, String> map = new HashMap<>();
        //获取定向人群 api
//        map.put(baseUrl + "/api/mgr/tencent/create/target", "{\"accountId\":16961308,\"targetingId\":3188569276}");
        //获取定向人气列表 api
        map.put(baseUrl + "/api/mgr/tencent/create/targets", "{\"accountId\":16961308,\"targetingName\":\"\"}");
        //获取跳转页 api
        map.put(baseUrl + "/api/mgr/tencent/create/profiles", "{\"accountId\":16961308}");
        //获取商品库列表 api
        map.put(baseUrl + "/api/mgr/tencent/create/productCatalogs", "[{\"accountId\":16961308}]");
        //行动按钮列表
        //map.put(baseUrl + "/api/mgr/tencent/create/actionBtnTexts", "true");
        //品牌形象列表 api
        map.put(baseUrl + "/api/mgr/tencent/create/brands", "{\"accountId\":16961308}");
        //获取腾讯的动态词包
//        map.put(baseUrl + "/api/mgr/tencent/create/trendsWords", "{}");
        //获取商品库里商品列表 api
        map.put(baseUrl + "/api/mgr/tencent/create/products", "{\"accountId\":16961308,\"productCatalogId\":268724245,\"productName\":\"\"}");
        ExecutorService exec = Executors.newFixedThreadPool(map.keySet().size());
        for (String url : map.keySet()) {
            Runnable task = () -> {
                extracted(url, map.get(url));
            };
            exec.submit(task);
        }

        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    @Test
    void contextLoad() {
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
        // 方式一
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        // 添加额外http请求头参数
        headers.add("token", "1440947183:380c4d2c167c4d5c82b88515faaede1da566a0a4552ca9639e5f8b08f669aed2ed091333Yh6Ud");
        HttpEntity<String> request = new HttpEntity<String>(param, headers);
        while (true) {
            ResponseEntity<String> stringResponseEntity = RestTemplateUtils.post(url, request, String.class);
//            System.out.println(stringResponseEntity);
            System.out.println(url.substring(url.lastIndexOf("/")) +":\t"+ stringResponseEntity.getBody());
        }
    }
}
