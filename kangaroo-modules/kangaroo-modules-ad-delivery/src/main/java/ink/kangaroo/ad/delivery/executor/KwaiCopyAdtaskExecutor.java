package ink.kangaroo.ad.delivery.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shencaozuo.common.RpcResult;
import com.shencaozuo.kwai.service.KwaiAdUnitService;
import com.shencaozuo.kwai.service.PlatformAccountService;
import ink.kangaroo.ad.delivery.mapper.AdTaskExecutionResultMapper;
import ink.kangaroo.ad.delivery.mapper.AdTaskMapper;
import ink.kangaroo.ad.delivery.model.entity.AdTask;
import ink.kangaroo.ad.delivery.model.entity.AdTaskExecutionResult;
import ink.kangaroo.common.core.utils.DateUtils;
import ink.kangaroo.common.ekuaishou.factory.KwaiAds;
import ink.kangaroo.common.ekuaishou.model.adunit.param.GetAdUnitInfoParam;
import ink.kangaroo.common.ekuaishou.model.base.BaseResult;
import ink.kangaroo.common.redis.service.RedisService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/16 17:46
 */
@Slf4j
public class KwaiCopyAdtaskExecutor extends AbstractCopyAdTaskExecutor {

    private final String SUCCESS_COUNT = "successCount";
    private Map<String, Object> data;

    @Setter
    @Getter
    private AdTaskExecutionResult executionResult;
    private KwaiAds kwaiAds;
    private KwaiAdUnitService adUnitService;
    private PlatformAccountService platformAccountService;
    private AdTaskMapper adTaskMapper;
    private AdTaskExecutionResultMapper resultMapper;
    private RedisService redisService;

    public KwaiCopyAdtaskExecutor(KwaiAds kwaiAds, PlatformAccountService platformAccountService, AdTaskMapper adTaskMapper, AdTaskExecutionResultMapper resultMapper, RedisService redisService) {
        this.kwaiAds = kwaiAds;
        this.platformAccountService = platformAccountService;
        this.redisService = redisService;
        this.adTaskMapper = adTaskMapper;
        this.resultMapper = resultMapper;
    }


    @Override
    public void start() {
        try {
            getAdTask().setExecutionTime(LocalDateTime.now());
            switch (getStatus()) {
                case 0:
                    setStatus(0);
                    initAccessToken();
                case 1:
                    setStatus(1);
                    cacheExecutionStatus();
                    initCampaign();
                case 2:
                    setStatus(2);
                    cacheExecutionStatus();
                    initAdUnit();
                case 3:
                    setStatus(3);
                    cacheExecutionStatus();
                    initCreative();
                case 4:
                    setStatus(4);
                    cacheExecutionStatus();
                    createAdUnit();
                case 5:
                    setStatus(5);
                    cacheExecutionStatus();
                    finishPart();
                case 6:
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

    /**
     * 广告完成之后 进行收尾更新状态
     */
    private void finishPart() {
        //更新任务状态
        AdTask adTask = getAdTask();
        adTask.setTaskStatus(900);
        AdTaskExecutionResult adTaskExecutionResult = resultMapper.selectById(adTask.getExecutionId());
        if (adTaskExecutionResult == null) {
            adTaskExecutionResult = new AdTaskExecutionResult();
            adTaskExecutionResult.setId(adTask.getExecutionId());
            adTaskExecutionResult.setAdTaskId(adTask.getId());
        }
        adTaskExecutionResult.setSuccessCount(getSuccessCount());
        adTaskExecutionResult.setFailedCount(getTotalCount() - getSuccessCount());
        adTaskExecutionResult.setUpdateTime(LocalDateTime.now());
        adTaskExecutionResult.setExecutionTime(getAdTask().getExecutionTime());
        resultMapper.updateById(adTaskExecutionResult);
        adTaskMapper.updateById(adTask);

    }


    @Override
    public void addSuccessRecord(int count) {
        Integer successCount = getSuccessCount();
        if (successCount != null) {
            setSuccessCount(successCount + count);
            return;
        }
        setSuccessCount(0);
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

    @Override
    public Integer getTotalCount() {
        return (Integer) getDateByKey("totalCount");
    }

    @Override
    public void setTotalCount(Integer totalCount) {
        setDateByKey("totalCount", totalCount);
    }

    @Override
    public Object getDateByKey(String key) {
        if (data == null) {
            return null;
        }
        return data.get(key);
    }

    @Override
    public void setDateByKey(String key, Object o) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, o);
    }

    @Override
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

    @Override
    public void setAdTask(AdTask adTask) {
        setDateByKey("adTask", adTask);
        setDateByKey("accountId", adTask.getAccountId());
        setDateByKey("adUnitId", adTask.getAdUnitId());
        setDateByKey("totalCount", adTask.getTotalCount());

    }

    @Override
    public void initAccessToken() {
        String accessToken = kwaiAds.getAccessToken(String.valueOf(getAdTask().getDeliveryAccount()));
        log.info("[accessToken -> {}]", accessToken);
        setAccessToken(accessToken);
    }

    @Override
    public void initCampaign() {

    }

    @Override
    public void initAdUnit() {
        try {
            String adString = null;
            GetAdUnitInfoParam getAdUnitInfoParam = new GetAdUnitInfoParam();
            getAdUnitInfoParam.setAdvertiserId(getAdTask().getDeliveryAccount());
            getAdUnitInfoParam.setUnitIds(Collections.singletonList(getAdUnitId()));

            BaseResult<JSONObject> adUnitInfo = kwaiAds.getAdUnitInfo(getAdUnitInfoParam);
            if (adUnitInfo.getCode() != null && adUnitInfo.getCode() == 0) {
                adString = adUnitInfo.getData().getJSONArray("details").get(0).toString();
                setAdSring(adString);
            }
            log.info("[初始化广告信息：adUnitId -> {} , adString -> {}]", getAdUnitId(), adString);
        } catch (Exception e) {
//            if (e instanceof RpcException) {
            e.printStackTrace();
//            }
            cacheExecutionStatus();
        }
    }

    private Long getAdUnitId() {
        Object adUnitId = data.get("adUnitId");
        if (adUnitId instanceof Integer) {
            return Long.valueOf((Integer) adUnitId);
        }
        return (Long) adUnitId;
    }

    @Override
    public void initCreative() {
//        log.info("[初始化创意信息：adString -> {}]", adString);
    }

    @Override
    public void createAdUnit() {
        try {

            log.info("[开始创建广告]:this -> {}", this);
            //解析参数
            JSONObject jsonObject = JSON.parseObject(getAdSring());
            jsonObject.put("advertiser_id", getAdTask().getDeliveryAccount());
            /**
             * {
             *     "code": 401000,
             *     "message": "put_status要么不传，要么传2(暂停状态)",
             *     "data": {},
             *     "request_id": "1e0b9e3c8bf944b7b86e036af2799b73"
             * }
             */
            jsonObject.put("put_status", 2);
            /**
             * {
             *     "code": 401000,
             *     "message": "begin_time不能小于当前时间",
             *     "data": {},
             *     "request_id": "fb85c051a7644d3a8aa612f3ac263a2c"
             * }
             *
             * 格式为 yyyy-MM-dd，需大于等于当前时间
             */
            //开始根据原投放时间来生成 新广告的投放时间
            /**
             * TODO 要在获取广告信息的时候进行校验
             *
             * 3. 投放日期：非必填，只能选择今天以及今天之后的日期
             * ①若原广告，排期选择的为 从今天开始长期投放，则复制后仍为从今天开始长期投放；
             * ②若原广告选择的为设置开始和结束日期，则投放日期的开始日期为填写的日期，结束日期则根据原广告的时间跨度计算出结束日期；2e.g：原广告投放日期为：2020-06-01 至 2020-06-10，设置的投放日期为2020-06-11，则复制的广告投放日期为：2020-06-11 至 2020-06-20.
             * 若没有填写投放日期，则开始日期默认为今天，结束日期则根据原广告的时间跨度计算出结束日期；
             */
            String date = DateUtils.getDate();
            String beginTime = jsonObject.getString("begin_time");
            String endTime = jsonObject.getString("end_time");
            if (!StringUtils.isBlank(getAdTask().getReleaseDate())) {
                if (StringUtils.isBlank(beginTime)) {
                    jsonObject.put("begin_time", date);
                } else {
                    if (StringUtils.isBlank(endTime)) {
                        int i = daysBetween(beginTime, endTime);
                        jsonObject.put("end_time", plugDay(date, i));
                    }
                }
            } else {
                jsonObject.put("begin_time", date);
            }
            //修改出价
            if (getAdTask().getBid() != null && getAdTask().getBid() > 0) {
                jsonObject.put("bid", getAdTask().getBid());
            }

            String unitName = jsonObject.getString("unit_name");
            log.info("[adString]:adString -> {}", getAdSring());
            for (Integer i = getSuccessCount(); i < getTotalCount(); i++) {
                jsonObject.put("unit_name", unitName + getAdTask().getSuffix() + i);
                RpcResult<JSONObject> adUnit = adUnitService.createAdUnit(getAccessToken(), jsonObject.toString());
                log.info("[创建广告完成]:adUnit -> {}", adUnit);
                addSuccessRecord(1);
                cacheExecutionStatus();
            }
            log.info("[创建广告完成]:this -> {}", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAdSring() {
        return String.valueOf(getDateByKey("adString"));
    }

    private void setAdSring(String adString) {
        setDateByKey("adString", adString);
    }

    @Override
    public void cacheExecutionStatus() {
        redisService.setCacheObject(ABSTRACT_AD_TASK_EXECUTOR + getAdTask().getExecutionId(), this.data);
        log.info("执行任务到阶段：status -> {}", getStatus());
    }

    /**
     * 字符串日期格式的计算
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String plugDay(String date, int i) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR, 10);//日期加10天
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    public Integer getStatus() {
        Integer status = (Integer) getDateByKey("status");
        if (status == null) {
            return 0;
        }
        return status;
    }

    public void setStatus(Integer status) {
        setDateByKey("status", status);
    }

    public String getAccessToken() {
        return (String) getDateByKey("accessToken");
    }

    public void setAccessToken(String accessToken) {
        setDateByKey("accessToken", accessToken);
    }


    @Override
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


}
