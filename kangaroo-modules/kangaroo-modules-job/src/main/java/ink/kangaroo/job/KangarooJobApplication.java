package ink.kangaroo.job;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableRyFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/3 17:54
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class KangarooJobApplication {
}
