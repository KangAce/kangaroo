package ink.kangaroo.trying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/24 23:53
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KangarooTryingApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooTryingApplication.class,args);
    }
}
