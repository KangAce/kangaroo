package ink.kangaroo.reminders;

import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:30
 */
@SpringBootApplication/*(exclude = DataSourceAutoConfiguration.class)*/
@EnableCustomConfig
public class KangarooRemindersApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooRemindersApplication.class, args);
    }
    @Bean
    RestTemplate restTemplate(){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        return new RestTemplate(requestFactory);
    }
}
