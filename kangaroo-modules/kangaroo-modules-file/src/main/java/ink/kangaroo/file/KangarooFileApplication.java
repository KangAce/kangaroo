package ink.kangaroo.file;

import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/24 23:53
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KangarooFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooFileApplication.class, args);
    }
}
