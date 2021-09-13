package ink.kangaroo.pixiv;

import lombok.Getter;

/**
 * 任务类型
 * @author zjw
 * @since 2021/3/30
 */
@Getter
public enum TaskTypeEnum {
    /**
     * 复制任务
     */
    TASK_COPY,
    /**
     * 创建任务
     */
    TASK_CREATE,
    ;
}
