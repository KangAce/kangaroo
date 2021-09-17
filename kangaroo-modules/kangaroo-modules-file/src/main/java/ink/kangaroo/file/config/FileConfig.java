package ink.kangaroo.file.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:25
 */
@Configuration
public class FileConfig {
    //HttpComponentsClientRestfulHttpRequestFactory
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate httpsRestTemplate = builder.build();
        httpsRestTemplate.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());
        return httpsRestTemplate;
    }


    // 设置超时时间
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // Connect timeout 3s
        clientHttpRequestFactory.setConnectTimeout(60000);
        // Read timeout 3s
        clientHttpRequestFactory.setReadTimeout(60000);
        return clientHttpRequestFactory;
    }

}
