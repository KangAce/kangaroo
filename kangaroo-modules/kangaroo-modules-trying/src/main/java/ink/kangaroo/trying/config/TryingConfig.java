package ink.kangaroo.trying.config;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:25
 */
@Configuration
public class TryingConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate httpsRestTemplate = builder.build();
        httpsRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return httpsRestTemplate;
    }

//    @Bean
//    public RocketMQTemplate rocketMQTemplate(RocketMQTemplate rocketMQTemplate) {
//        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
//        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        return rocketMQTemplate;
//    }
}
