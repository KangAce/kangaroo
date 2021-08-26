package ink.kangaroo.reminders;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:29
 */
@SpringBootTest
@Rollback(value = true)
@Transactional
public class remindersTest {

    @Resource
    RestTemplate restTemplate;
    public final static String SERVICE_UUID = UUID.randomUUID().toString().replace("-", "");

    @Test
    void contextLoad() {
        String url = "http://192.168.1.107:91/api/mgr/tencent/create/target";
        String param = "{\"accountId\":16961308,\"targetingId\":3188569276}";
        // 方式一
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        // 添加额外http请求头参数
        headers.add("token","1497703661:b846091b3e464d66b2d79dedda8c7ebea566a0a4552ca9639e5f8b08f669aed2ed091333Z3rYO");
        HttpEntity<String> request = new HttpEntity<String>(param, headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(stringResponseEntity);
        System.out.println(stringResponseEntity.getBody());
//        http://192.168.1.107:91/api/mgr/tencent/create/targets
//
//        朋友圈头像昵称跳转页列表

//        http://192.168.1.107:91/api/mgr/tencent/create/profiles

//        推广页模版列表

//        http://192.168.1.107:91/api/mgr/tencent/create/pages

//        获取商品库列表

//        http://192.168.1.107:91/api/mgr/tencent/create/productCatalogs
//
//
//
//        http://192.168.1.107:91/api/mgr/tencent/create/actionBtnTexts
//
//
//
//        http://192.168.1.107:91/api/mgr/tencent/create/productCatalogs
//
//
//
//        http://192.168.1.107:91/api/mgr/tencent/create/brands
//
//
//
//        http://192.168.1.107:91/api/mgr/tencent/create/trendsWords
//
//
//
//        http://192.168.1.107:91/api/mgr/tencent/create/products
        System.out.println(SERVICE_UUID);

    }
}
