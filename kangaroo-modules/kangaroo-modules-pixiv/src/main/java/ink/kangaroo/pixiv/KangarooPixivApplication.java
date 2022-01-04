package ink.kangaroo.pixiv;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ink.kangaroo.common.security.annotation.EnableCustomConfig;
import ink.kangaroo.common.security.annotation.EnableKangarooFeignClients;
import ink.kangaroo.common.swagger.annotation.EnableCustomSwagger2;
import ink.kangaroo.common.jpa.repository.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 22:18
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableKangarooFeignClients
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ink.kangaroo.pixiv.repository", repositoryBaseClass = BaseRepositoryImpl.class)
public class KangarooPixivApplication {
    public static void main(String[] args) {
        SpringApplication.run(KangarooPixivApplication.class, args);
    }
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
    @Bean
    RestTemplate restTemplate(){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        return new RestTemplate(requestFactory);
    }
}
