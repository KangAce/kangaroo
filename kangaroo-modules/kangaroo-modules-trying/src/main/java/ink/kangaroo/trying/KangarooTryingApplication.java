package ink.kangaroo.trying;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.Resource;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/24 23:53
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KangarooTryingApplication /*implements CommandLineRunner */{
//    @Resource
//    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(KangarooTryingApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        rocketMQTemplate.convertAndSend("kangaroo-try", "Hello, World!");
//        rocketMQTemplate.send("kangaroo-try", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//    }
}
