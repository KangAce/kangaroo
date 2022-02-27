package ink.kangaroo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 16:59
 */
@EnableFeignClients(basePackages = "ink.kangaroo")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KangarooGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooGatewayApplication.class, args);
    }
}
