package ink.kangaroo.ad.delivery.executor;

import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.ad.delivery.model.entity.AdTask;
import ink.kangaroo.ad.delivery.model.entity.AdTaskExecutionResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务抽象执行者
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/16 17:37
 */
@Data
@Slf4j
public abstract class AbstractAdTaskExecutor {
    public static String ABSTRACT_AD_TASK_EXECUTOR = "abstract-ad-task-executor:";
    private final String SUCCESS_COUNT = "successCount";
    private final String IS_BREAK = "isBreak";
    private final String STATUS = "status";
    private final String ACCESS_TOKEN = "accessToken";
    private final String AD_TASK = "adTask";
    private final String SUCCESS_AD_INFO = "successAdInfo";

    Map<String, Object> data;
    @Setter
    @Getter
    private AdTaskExecutionResult executionResult;

    protected boolean isBreak() {
        Boolean isBreak = (Boolean) getDateByKey(IS_BREAK);

        if (isBreak == null) {
            return false;
        }
        return isBreak;
    }

    protected void setBreak(Boolean isBreak) {
        if (isBreak == null) {
            return;
        }
        setDateByKey(IS_BREAK, isBreak);
    }

    public Integer getStatus() {
        Integer status = (Integer) getDateByKey(STATUS);
        if (status == null) {
            return 0;
        }
        return status;
    }

    public void setStatus(Integer status) {
        setDateByKey(STATUS, status);
    }


    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getDateByKey(String key) {
        if (data == null) {
            return null;
        }
        return data.get(key);
    }

    public void setDateByKey(String key, Object o) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, o);
    }

    public String getAccessToken() {
        return (String) getDateByKey(ACCESS_TOKEN);
    }

    public void setAccessToken(String accessToken) {
        setDateByKey(ACCESS_TOKEN, accessToken);
    }

    public AdTask getAdTask() {
        Object adTaskObj = getDateByKey("adTask");
        if (adTaskObj == null) {
            return null;
        }
        if (adTaskObj instanceof AdTask) {
            return (AdTask) adTaskObj;
        }
        if (adTaskObj instanceof JSONObject) {
            JSONObject adTaskJSONObj = (JSONObject) adTaskObj;
            return adTaskJSONObj.toJavaObject(AdTask.class);
        }
        return null;
    }

    public void setAdTask(AdTask adTask) {
        setDateByKey(AD_TASK, adTask);
        setDateByKey("accountId", adTask.getAccountId());
        setDateByKey("adUnitId", adTask.getAdUnitId());
        setDateByKey("totalCount", adTask.getTotalCount());

    }

    public List<AdInfo> getSuccessAdInfo() {
        List<AdInfo> successAdInfo = (List<AdInfo>) getDateByKey(SUCCESS_AD_INFO);
        if (successAdInfo == null) {
            return new ArrayList<>();
        }
        return successAdInfo;
    }

    public void setSuccessAdInfo(List<AdInfo> successAdInfo) {
        if (successAdInfo == null) {
            return;
        }
        setDateByKey(SUCCESS_AD_INFO, successAdInfo);
    }

    public Integer getSuccessCount() {
        Integer successCount = (Integer) getDateByKey(SUCCESS_COUNT);
        if (successCount == null) {
            return 0;
        }
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        setDateByKey(SUCCESS_COUNT, successCount);
    }

    public void addSuccessRecord(int count) {
        Integer successCount = getSuccessCount();
        if (successCount != null) {
            setSuccessCount(successCount + count);
            return;
        }
        setSuccessCount(0);
    }


    public Integer getTotalCount() {
        return (Integer) getDateByKey("totalCount");
    }

    public void setTotalCount(Integer totalCount) {
        setDateByKey("totalCount", totalCount);
    }

    /**
     * 初始化token
     */
    abstract void initAccessToken();

    /**
     * 开始执行任务
     */
    public void start() {
        try {
            getAdTask().setExecutionTime(LocalDateTime.now());
            switch (getStatus()) {
                case 0:
                    setStatus(0);
                    initAccessToken();
                    if (isBreak()) {
                        break;
                    }
                case 1:
                    setStatus(1);
                    cacheExecutionStatus();
                    initCampaign();
                    if (isBreak()) {
                        break;
                    }
                case 2:
                    setStatus(2);
                    cacheExecutionStatus();
                    initAdUnit();
                    if (isBreak()) {
                        break;
                    }
                case 3:
                    setStatus(3);
                    cacheExecutionStatus();
                    initCreative();
                    if (isBreak()) {
                        break;
                    }
                case 4:
                    setStatus(4);
                    cacheExecutionStatus();
                    createCampaign();
                    if (isBreak()) {
                        break;
                    }
                case 5:
                    setStatus(4);
                    cacheExecutionStatus();
                    createAdUnit();
                    if (isBreak()) {
                        break;
                    }
                case 6:
                    setStatus(4);
                    cacheExecutionStatus();
                    createCreative();
                    if (isBreak()) {
                        break;
                    }
                case 7:
                    setStatus(5);
                    cacheExecutionStatus();
                    finishPart();
                    if (isBreak()) {
                        break;
                    }
                case 8:
                    setStatus(6);
                    cacheExecutionStatus();
                default:
                    log.info("广告完成");
                    break;
            }
        } catch (Exception e) {
            //写一个统一异常处理方法
            e.printStackTrace();

        } finally {
            finishPart();
        }
    }

    protected abstract void createCreative();

    protected abstract void createCampaign();

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
    abstract void cacheExecutionStatus();

    /**
     * 广告完成之后 进行收尾更新状态
     */
    abstract void finishPart();
}
