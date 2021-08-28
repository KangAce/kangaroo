package ink.kangaroo.file;

import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.file.domain.M3U8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/24 23:53
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KangarooFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooFileApplication.class,args);
//        m3U8.setDir("D://m3u8JavaTest");
//        m3U8.setFileName("Test.mp4");
//        m3U8.go();
    }
}
