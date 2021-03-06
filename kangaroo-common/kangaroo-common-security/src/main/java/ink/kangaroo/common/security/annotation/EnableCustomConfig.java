package ink.kangaroo.common.security.annotation;

import ink.kangaroo.common.security.config.ApplicationConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/3 17:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("ink.kangaroo.**.mapper")
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ApplicationConfig.class, FeignAutoConfiguration.class})
public @interface EnableCustomConfig {

}
