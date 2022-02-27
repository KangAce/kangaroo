package ink.kangaroo.captcha;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarooModulesCaptchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KangarooModulesCaptchaApplication.class, args);
    }

}
