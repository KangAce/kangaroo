package ink.kangaroo.douyin;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarooDouyinApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooDouyinApplication.class, args);
    }
}
