package ink.kangaroo.system;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 20:42
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarroSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarroSystemApplication.class, args);
    }
}
