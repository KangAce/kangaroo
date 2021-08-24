package ink.kangaroo.reminders;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:30
 */
@EnableCustomConfig
public class KangarooRemindersApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooRemindersApplication.class, args);
    }
}
