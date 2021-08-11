package ink.kangaroo.pixiv;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 22:18
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarooPixivApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooPixivApplication.class, args);
    }
}
