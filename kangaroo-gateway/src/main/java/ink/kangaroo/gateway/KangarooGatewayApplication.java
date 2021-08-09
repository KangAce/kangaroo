package ink.kangaroo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 16:59
 */
@EnableWebFluxSecurity
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KangarooGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooGatewayApplication.class, args);
    }
}
