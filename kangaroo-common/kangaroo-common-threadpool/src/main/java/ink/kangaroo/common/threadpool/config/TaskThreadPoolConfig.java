package ink.kangaroo.common.threadpool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置属性类
 *
 * @author Kangaroo
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig {

    /**
     * 核心线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 20;

    /**
     * 线程空闲时间
     */
    private int keepAliveSeconds = 300;

    /**
     * 任务队列容量（阻塞队列）
     */
    private int queueCapacity = 50;
}