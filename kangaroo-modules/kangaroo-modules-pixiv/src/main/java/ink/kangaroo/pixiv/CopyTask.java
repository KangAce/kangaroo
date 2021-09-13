package ink.kangaroo.pixiv;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 复制广告任务
 *
 * @author zhanglei
 * @since 2020-07-09
 */
@Data
@Document(value = "copy_task")
public class CopyTask implements Serializable {

    private static final long serialVersionUID = 4417774033857044859L;

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 腾讯授权账户id
     */
    @Field("account_id")
    @Indexed
    private Long accountId;

    /**
     * 推广计划id
     */
    @Field("campaign_id")
    private Long campaignId;

    /**
     * 天运当前登录用户id
     */
    @Field("seller_id")
    private String sellerId;

    /**
     * 广告id
     */
    @Field("ad_id")
    private Long adId;

    /**
     * 广告名称
     */
    @Field("ad_name")
    private String adName;

    /**
     * 后缀
     */
    @Field("suffix")
    private String suffix;

    /**
     * 出价
     */
    @Field("bid_amount")
    private Long bidAmount;

    /**
     * 复制条数
     */
    @Field("number")
    private Long number;

    /**
     * 状态(0:未开始, 1:正在复制, 2:已完成，3api超时重试（五分钟），4视频上传超时重试（三十分钟）)
     */
    @Field("state")
    private Integer state;

    /**
     * 成功条数
     */
    @Field("success_num")
    private Long successNum;

    /**
     * 失败条数
     */
    @Field("fail_num")
    private Long failNum;

    /**
     * 创建时间
     */
    @Field("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Field("update_time")
    private LocalDateTime updateTime;

    /**
     * 更新时间
     */
    @Field("last_execution_time")
    private LocalDateTime lastExecutionTime;

    /**
     * 投放时间
     */
    @Field("launch_time")
    private LocalDate launchDate;

    /**
     * 是否已被删除(false:未被删除, true:已被删除)
     */
    @Field("is_deleted")
    private Boolean isDeleted;

    /**
     * 错误信息列表(返回前端时需要按错误码去重)
     */
    @Field("tencent_error_msgs")
    private List<TencentErrorMsg> tencentErrorMsgs;

    @Field("ad_group_set")
    private Set<Long> adGroups;

    /**
     * 创建广告的Id
     */
    @Field("create_task_id")
    private String createTaskId;
    /**
     * 任务类型
     */
    @Field("task_type")
    private TaskTypeEnum taskType;

    /**
     * 创建广告模板Id
     */
    @Field("template_id")
    private String templateId;

    @Field("is_timer_create")
    @ApiModelProperty("是否定时创建")
    private Boolean isTimerCreate = false;

    @Field("is_timing_enable")
    @ApiModelProperty("是否开始定时")
    private Boolean isTimerEnable;

    @Field("timer_time")
    @ApiModelProperty("定时时间")
    private String timerTime;

    @Field("timer_type")
    @ApiModelProperty("定时类型(0仅一次 1每周 2每天)")
    private Integer timerType;

    @Field("timer1_weeks")
    @ApiModelProperty("1-7 代表周一到周日")
    private List<Integer> timer1Weeks;

}
