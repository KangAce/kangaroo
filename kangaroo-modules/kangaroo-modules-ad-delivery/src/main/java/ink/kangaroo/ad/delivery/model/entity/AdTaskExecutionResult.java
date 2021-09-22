package ink.kangaroo.ad.delivery.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务执行结果
 * </p>
 *
 * @author auto generator
 * @since 2021-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdTaskExecutionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 执行结果ID
     */
    private Long id;

    /**
     * 广告任务ID
     */
    private Long adTaskId;

    /**
     * 广告成功数量
     */
    private Integer successCount;

    /**
     * 广告失败数量
     */
    private Integer failedCount;

    /**
     * 广告任务执行时间
     */
    private LocalDateTime executionTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
