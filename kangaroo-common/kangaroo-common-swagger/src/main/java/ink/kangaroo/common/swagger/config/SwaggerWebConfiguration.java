package ink.kangaroo.common.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * swagger 资源映射路径
 * @author kbw
 * @version 1.0
 * @date 2021/8/2 11:20
 */
@Configuration
public class SwaggerWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** swagger-ui 地址 */
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
