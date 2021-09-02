package ink.kangaroo.pixiv.model;

import lombok.Data;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/2 10:19
 */

@Data
@Document(value = "temporary")
public class Temporary implements Serializable {

    /**
     * 主键定时
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * coptTaskId
     */
    @Field("task_id")
    @Indexed
    private String taskId;

    /**
     * 复制条数
     */
    @Field("number")
    private Long number;

    /**
     * 状态(0:未开始, 1:正在复制, 2:已完成)
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
     * 投放时间 执行时间
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
//    @Field("tencent_error_msgs")
//    private List<TencentErrorMsg> tencentErrorMsgs;

    @Field("ad_group_set")
    private Set<Long> adGroups;

    /**
     * 创建广告的Id
     */
    @Field("create_task_id")
    private String createTaskId;
    /**
     * 执行记录的执行时间
     */
    @Field("execution_time")
    private LocalDateTime executionTime;
}
