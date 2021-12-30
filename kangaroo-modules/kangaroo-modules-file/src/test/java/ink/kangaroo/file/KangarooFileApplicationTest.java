package ink.kangaroo.file;


import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/26 0:03
 */
@SpringBootTest
//@Rollback(value = true)
//@Transactional
public class KangarooFileApplicationTest {

    @Resource
    RestTemplate restTemplate;

    @Test
    void contextLoads() throws ServiceException, IOException {
    }
}