package ink.kangaroo.ad.delivery.executor;

import com.shencaozuo.task.model.entity.AdTask;
import com.shencaozuo.task.model.entity.AdTaskExecutionResult;
import ink.kangaroo.ad.delivery.model.entity.AdTask;
import ink.kangaroo.ad.delivery.model.entity.AdTaskExecutionResult;
import lombok.Data;

import java.util.Map;

/**
 * 任务抽象执行者
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/16 17:37
 */
@Data
public abstract class AbstractAdTaskExecutor {
    public final static String ABSTRACT_AD_TASK_EXECUTOR = "abstract-ad-task-executor:";

    private Map<String, Object> data;

    abstract void addSuccessRecord(int count);

    public abstract Integer getTotalCount();

    public abstract void setTotalCount(Integer totalCount);

    public abstract Object getDateByKey(String key);

    public abstract void setDateByKey(String key, Object o);

    abstract AdTask getAdTask();

    public abstract AdTaskExecutionResult getExecutionResult();

    public abstract void setExecutionResult(AdTaskExecutionResult executionResult);

    abstract void setAdTask(AdTask adTask);

    /**
     * 初始化token
     */
    abstract void initAccessToken();

    /**
     * 开始执行任务
     */
    void start(){

    };

    /**
     * 初始化广告计划
     */
    abstract void initCampaign();

    /**
     * 初始化广告组
     */
    abstract void initAdUnit();

    /**
     * 初始化广告创意
     */
    abstract void initCreative();

    /**
     * 创建广告组
     */
    abstract void createAdUnit();

    /**
     * 保存执行状态
     */
    void cacheExecutionStatus() {

    }

}
