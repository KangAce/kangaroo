package ink.kangaroo.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/3 18:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableKangarooFeignClients
{
    String[] value() default {};

    String[] basePackages() default { "ink.kangaroo" };

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}