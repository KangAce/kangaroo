package ink.kangaroo.gen;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarooGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooGenApplication.class, args);
    }
}
