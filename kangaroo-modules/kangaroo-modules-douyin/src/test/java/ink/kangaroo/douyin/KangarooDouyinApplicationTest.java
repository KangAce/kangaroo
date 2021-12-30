package ink.kangaroo.douyin;

import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.douyin.common.service.DyOAuth2Service;
import ink.kangaroo.douyin.open.api.DyOpenComponentService;
import ink.kangaroo.douyin.open.api.DyOpenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */

@SpringBootTest
class KangarooDouyinApplicationTest {
    @Resource
    DyOAuth2Service dyOAuth2Service;
    @Resource
    DyOpenService dyOpenService;
    @Resource
    DyOpenComponentService dyOpenComponentService;
    //    @Test
    @Resource
    RestTemplate restTemplate;
    @Test
    void contextLoads() throws ServiceException, IOException {
//        dyOAuth2Service.buildAuthorizationUrl();
//        dyOAuth2Service.getAccessToken();

    }
}