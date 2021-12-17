package ink.kangaroo.ad.delivery.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author auto generator
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Long id;

    /**
     * 需要复制的任务ID
     */
    private Long adUnitId;

    /**
     * 广告组名称
     */
    private String adUnitName;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 后缀
     */
    private Long bid;

    /**
     * 投放账号
     */
    private Long deliveryAccount;

    /**
     * 投放平台 1快手
     */
    private Integer deliveryPlatform;

    /**
     * 任务类型 1创建 2复制
     */
    private Integer taskType;

    /**
     * 需要执行的总条数
     */
    private Integer totalCount;

    /**
     * 0任务创建中，100任务创建完成，200任务等待入队，300入队成功（排队中），400出队中，500出队成功，600任务执行中 ，900任务执行完成
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 执行时间
     */
    private LocalDateTime executionTime;

    /**
     * 主体ID
     */
    private Long accountId;
    /**
     * 用户在主体中的用户ID
     */
    private Long accountUserId;
    /**
     * 执行结果（记录）ID
     */
    private Long executionId;
    /**
     * 投放日期
     */
    private String releaseDate;

    //    @Field("is_timer_create")
    @ApiModelProperty("是否定时创建")
    private Boolean isTimerCreate = false;

    //    @Field("is_timing_enable")
    @ApiModelProperty("是否开始定时")
    private Boolean isTimerEnable;

    //    @Field("timer_time")
    @ApiModelProperty("定时时间")
    private String timerTime;

    //    @Field("timer_type")
    @ApiModelProperty("定时类型(0仅一次 1每周 2每天)")
    private Integer timerType;

    //    @Field("timer1_weeks")
    @ApiModelProperty("1-7 代表周一到周日")
    private List<Integer> timer1Weeks;



}
