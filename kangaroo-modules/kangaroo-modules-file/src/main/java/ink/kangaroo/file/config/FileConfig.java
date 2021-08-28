package ink.kangaroo.file.config;

import ink.kangaroo.file.M3U8Loader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:25
 */
@Configuration
public class FileConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate httpsRestTemplate = builder.build();
        httpsRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return httpsRestTemplate;
    }
}
