package ink.kangaroo.common.swagger.annotation;

import ink.kangaroo.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/2 11:01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableCustomSwagger2 {
}
