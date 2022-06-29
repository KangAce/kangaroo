package ink.kangaroo.modules.platform;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/3 18:19
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KangarooPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooPlatformApplication.class, args);
    }
}
