package ink.kangaroo.limiter;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableKangarooFeignClients
@SpringBootApplication
public class KangarooLimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooLimiterApplication.class, args);
    }
}
