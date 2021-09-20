package ink.kangaroo.file;


import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.file.domain.M3U8;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    M3U8Loader loader;
//    @Test
    void contextLoads() throws ServiceException, IOException, InterruptedException {
        M3U8 m3U8 = M3U8Loader.load("");
    }
}