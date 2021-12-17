//package ink.kangaroo.ad.delivery.executor;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import ink.kangaroo.ad.delivery.mapper.AdTaskExecutionResultMapper;
//import ink.kangaroo.ad.delivery.mapper.AdTaskMapper;
//import ink.kangaroo.common.core.utils.DateUtils;
//import ink.kangaroo.common.core.utils.bean.BeanUtils;
//import ink.kangaroo.common.ekuaishou.factory.KwaiAds;
//import ink.kangaroo.common.ekuaishou.model.adunit.param.CreateAdUnitParam;
//import ink.kangaroo.common.ekuaishou.model.adunit.param.GetAdUnitInfoParam;
//import ink.kangaroo.common.ekuaishou.model.adunit.result.AdUnitDetailsDataResult;
//import ink.kangaroo.common.ekuaishou.model.base.BaseResult;
//import ink.kangaroo.common.ekuaishou.model.exception.KwaiException;
//import ink.kangaroo.common.redis.service.RedisService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @author kbw
// * @version 1.0
// * @date 2021/9/16 17:46
// */
//@Slf4j
//public class KwaiCopyAdtaskExecutor extends AbstractCopyAdTaskExecutor {
//
//    public CreateAdUnitParam getCreateAdUnitParam() {
//        return createAdUnitParam;
//    }
//
//    public void setCreateAdUnitParam(CreateAdUnitParam createAdUnitParam) {
//        this.createAdUnitParam = createAdUnitParam;
//    }
//
//    CreateAdUnitParam createAdUnitParam;
//    private final KwaiAds kwaiAds;
//    private final AdTaskMapper adTaskMapper;
//    private final AdTaskExecutionResultMapper resultMapper;
//    private final RedisService redisService;
//
//
//    public KwaiCopyAdtaskExecutor(KwaiAds kwaiAds, AdTaskMapper adTaskMapper, AdTaskExecutionResultMapper resultMapper, RedisService redisService) {
//        this.kwaiAds = kwaiAds;
//        this.redisService = redisService;
//        this.adTaskMapper = adTaskMapper;
//        this.resultMapper = resultMapper;
//    }
//
//    @Override
//    public void initAccessToken() {
////        String accessToken = platformAccountService.getAccessToken(String.valueOf(getAdTask().getDeliveryAccount()));
////        log.info("[accessToken -> {}]", accessToken);
////        setAccessToken(accessToken);
////        String accessToken = kwaiAds.getAccessToken(String.valueOf(getAdTask().getDeliveryAccount()));
////        log.info("[accessToken -> {}]", accessToken);
////        setAccessToken(accessToken);
//    }
//
//    @Override
//    public void initCampaign() {
//
//    }
//
//    @Override
//    public void initAdUnit() {
//        try {
////            String adString = null;
//            GetAdUnitInfoParam getAdUnitInfoParam = new GetAdUnitInfoParam();
//            getAdUnitInfoParam.setAdvertiserId(getAdTask().getDeliveryAccount());
//            getAdUnitInfoParam.setUnitIds(Collections.singletonList(getAdTask().getAdUnitId()));
//            BaseResult<AdUnitDetailsDataResult> adUnitInfo = kwaiAds.getAdUnitInfo(getAdUnitInfoParam);
//            if (adUnitInfo.getCode() == 0) {
//                CreateAdUnitParam createAdUnitParam = new CreateAdUnitParam();
//                BeanUtils.copyBeanProp(createAdUnitParam, adUnitInfo.getData().getDetails().get(0));
////                adString = returnObject.getJSONObject("data").getJSONArray("details").get(0).toString();
//            }
//            log.info("[初始化广告信息：adUnitId -> {} , adString -> {}]", getAdUnitId(), adUnitInfo);
//        } catch (Exception e) {
////            if (e instanceof RpcException) {
//            e.printStackTrace();
////            }
//            cacheExecutionStatus();
//        }
//    }
//
//    private Long getAdUnitId() {
//        Object adUnitId = data.get("adUnitId");
//        if (adUnitId instanceof Integer) {
//            return Long.valueOf((Integer) adUnitId);
//        }
//        return (Long) adUnitId;
//    }
//
//
//    @Override
//    public void initCreative() {
////        log.info("[初始化创意信息：creativeString -> {}]", getCreativeString());
//        try {
//            List<String> createtiveStrings = new ArrayList<>();
//            BaseResult creativeInfo = null;
//            if (getCreateAdUnitParam().getUnitType() != null && getCreateAdUnitParam().getUnitType().equals(7) || getCreateAdUnitParam().getUnitType().equals(5)) {
//
//                creativeInfo = kwaiAds.getCreativeInfo2(getAdTask().getDeliveryAccount(), Collections.singletonList(getAdUnitId()));
//                if (ResultEnum.isOk(creativeInfo.getCode())) {
//
//                    String creativeInfoData = ;
//                    if (creativeInfoData != null) {
//                        JSONObject returnObject = JSON.parseObject(creativeInfoData);
//                        if (returnObject == null || returnObject.get("code") == null || returnObject.get("data") == null
//                                || returnObject.getInteger("code") != 0) {
//                            //记录异常
////                    return null;
//                            if (returnObject == null) {
//                                handleBaseException(new KwaiException("14978", null, "这是一个需要联系管理员的问题"));
//                            } else {
//                                handleBaseException(new KwaiException(returnObject.getString("code"), null, returnObject.getString("message")));
//                            }
//                            return;
//                        }
////                解析参数
//                        JSONObject data = returnObject.getJSONObject("data");
//                        JSONArray details = data.getJSONArray("details");
//                        if (details != null) {
//                            for (Object detail : details) {
//                                JSONObject detailJSONObject = (JSONObject) detail;
//                                //处理广告信息
//                                detailJSONObject.put("unit_id", getAdUnitId());
//                                detailJSONObject.put("advertiser_id", getAdTask().getDeliveryAccount());
//                                detailJSONObject.remove("creatives");
//                                createtiveStrings.add(detailJSONObject.toJSONString());
//                            }
//                            setCreatetiveStrings(createtiveStrings);
//                        } else {
//                            //记录异常，广告下无创意
//                            handleBaseException(new KwaiException("98649", null, "源广告创意获取失败"));
//                        }
////                return returnObject.getJSONObject("data");
//                    } else {
//                        //记录异常
//                        handleBaseException(new KwaiException("98464", null, "RPC异常"));
//                        //                return null;
//                    }
//                }
//            } else {
//                creativeInfo = kwaiAds.getCreativeInfo(getAccessToken(), getAdTask().getDeliveryAccount(), getAdUnitId());
//                if (ResultEnum.isOk(creativeInfo.getCode())) {
//                    String creativeInfoData = creativeInfo.getData();
//                    if (creativeInfoData != null) {
//                        JSONObject returnObject = JSON.parseObject(creativeInfoData);
//                        if (returnObject == null || returnObject.get("code") == null || returnObject.get("data") == null
//                                || returnObject.getInteger("code") != 0) {
//                            //记录异常
////                    return null;
//                            if (returnObject == null) {
//                                handleBaseException(new KwaiException("19874", null, "这是一个需要联系管理员的问题"));
//                            } else {
//                                handleBaseException(new KwaiException(returnObject.getString("code"), null, returnObject.getString("message")));
//                            }
//                            return;
//                        }
////                解析参数
//                        JSONObject data = returnObject.getJSONObject("data");
//                        JSONArray details = data.getJSONArray("details");
//                        if (details != null) {
//                            for (Object detail : details) {
//                                JSONObject detailJSONObject = (JSONObject) detail;
//                                //处理广告信息
//                                //处理广告信息
//                                detailJSONObject.put("unit_id", getAdUnitId());
//                                detailJSONObject.put("advertiser_id", getAdTask().getDeliveryAccount());
//                                detailJSONObject.remove("creatives");
//                                createtiveStrings.add(detailJSONObject.toJSONString());
//                            }
//                            setCreatetiveStrings(createtiveStrings);
//                        } else {
//                            //记录异常，广告下无创意
//                            handleBaseException(new KwaiException("98456", null, "源广告创意为0"));
//                        }
////                return returnObject.getJSONObject("data");
//                    } else {
//                        //记录异常
//                        handleBaseException(new KwaiException("98464", null, "RPC异常"));
//                        //                return null;
//                    }
//                }
//            }
//            log.info("Kwai获取创意接口调用成功：creativeInfo -> {}", creativeInfo);
//
//        } catch (Exception e) {
//            handleBaseException(e);
//        }
//    }
//
//    private void setCreatetiveStrings(List<String> createtiveStrings) {
//        setDateByKey("createtiveStrings", createtiveStrings);
//    }
//
//    private List<String> getCreatetiveStrings() {
//        List<String> createtiveStrings = (List<String>) getDateByKey("createtiveStrings");
//        if (createtiveStrings == null) {
//            return new ArrayList<>();
//        }
//        return createtiveStrings;
//    }
//
//    @Override
//    public void createAdUnit() {
//        try {
//
//            log.info("[开始创建广告]:this -> {}", this);
//            //解析参数
//            JSONObject jsonObject = JSON.parseObject(getAdSring());
//            String unitName = jsonObject.getString("unit_name");
//            log.info("[adString]:adString -> {}", getAdSring());
//            AdInfo adInfo = null;
//            try {
//                for (Integer i = getSuccessCount(); i < getTotalCount(); i++) {
//                    adInfo = new AdInfo();
//                    adInfo.setStatus(101);
//                    jsonObject.put("unit_name", unitName + getAdTask().getSuffix() + i);
//                    //同计划下复制广告组
//                    RpcResult<JSONObject> adUnit = kwaiAds.createAdUnit(getAccessToken(), jsonObject.toString());
//                    if (ResultEnum.isOk(adUnit.getCode())) {
//                        JSONObject returnObject = adUnit.getData();
//                        if (returnObject == null || returnObject.get("code") == null || returnObject.get("data") == null
//                                || returnObject.getInteger("code") != 0) {
//                            //异常
//                            if (returnObject == null) {
//                                handleBaseException(new KwaiException("16534", null, "这是一个需要联系管理员的问题"));
//                            } else {
//                                handleBaseException(new KwaiException(returnObject.getString("code"), null, returnObject.getString("message")));
//                            }
//                            return;
//                        }
//                        //记录创建成功的广告组
//                        Long unitId = adUnit.getData().getJSONObject("data").getLong("unit_id");
//                        adInfo.setAdUnitId(unitId);
//                    }
//                    adInfo.setStatus(301);
//                    //复制创意
//                    log.info("[循环创意：] 获取到的创意数量 - > {} ，" +
//                            "getCreatetiveStrings() - > {}", getCreatetiveStrings().size(), getCreatetiveStrings());
//
//                    for (String createtiveString : getCreatetiveStrings()) {
//                        RpcResult<String> creativeInfo = null;
//                        if (JSON.parseObject(getAdSring()).getInteger("unit_type") != null && (JSON.parseObject(getAdSring()).getInteger("unit_type").equals(7) || JSON.parseObject(getAdSring()).getInteger("unit_type").equals(5))) {
//                            JSONObject detailJSONObject = JSON.parseObject(createtiveString);
//                            detailJSONObject.put("unit_id", adInfo.getAdUnitId());
//                            creativeInfo = creativeService.createCreative2(getAccessToken(), detailJSONObject.toJSONString());
//                        } else {
//                            JSONObject detailJSONObject = JSON.parseObject(createtiveString);
//                            detailJSONObject.put("unit_id", adInfo.getAdUnitId());
//                            creativeInfo = creativeService.createCreative(getAccessToken(), detailJSONObject.toJSONString());
//                        }
//                        if (ResultEnum.isOk(creativeInfo.getCode())) {
//                            String creativeInfoData = creativeInfo.getData();
//                            if (creativeInfoData != null) {
//                                JSONObject returnObject = JSON.parseObject(creativeInfoData);
//                                if (returnObject == null || returnObject.get("code") == null || returnObject.get("data") == null
//                                        || returnObject.getInteger("code") != 0) {
//                                    //记录异常
//                                    //return null;
//                                    handleBaseException(new KwaiException(returnObject.getString("code"), null, returnObject.getString("message")));
//                                    break;
//                                }
//                                //解析参数
//                                JSONObject data = returnObject.getJSONObject("data");
//                                Long creativeId = data.getLong("creative_id");
//                                List<Long> creativeIds = adInfo.getCreativeIds();
//                                if (creativeIds == null) {
//                                    creativeIds = new ArrayList<>();
//                                }
//                                creativeIds.add(creativeId);
//                                //return returnObject.getJSONObject("data");
//                            } else {
//                                //记录异常
//                                handleBaseException(new KwaiException("13456", null, "RPC异常"));
//                                break;
//                                //return null;
//                            }
//
//                        }
//                    }
//                    //如果是异常导致的跳出则不修改状态为完成，否则完成阶段会认为广告创建成功，不会当作创建失败的广告去删除。
//                    if (!isBreak()) {
//                        adInfo.setStatus(901);
//                        addSuccessRecord(1);
//                    }
//                    List<AdInfo> successAdInfo = getSuccessAdInfo();
//                    successAdInfo.add(adInfo);
//                    setSuccessAdInfo(successAdInfo);
//                    //adUnit.getData().get("")
//                    log.info("[创建广告完成]:adUnit -> {},adInfo -> {}", adUnit, adInfo);
//                    cacheExecutionStatus();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                //记录当前创建的广告信息，用于结尾阶段删除未完全创建成功的相关信息
//                List<AdInfo> successAdInfo = getSuccessAdInfo();
//                successAdInfo.add(adInfo);
//                setSuccessAdInfo(successAdInfo);
//                log.info("[创建广告异常，当前广告创建信息保存]:adInfo -> {}", adInfo);
//                handleBaseException(e);
//            }
//            log.info("[创建广告完成]:this -> {}", data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (e instanceof RpcException) {
//                log.info("dubbo 服务调用异常");
//            }
//            handleBaseException(e);
//        }
//    }
//
//    void finishPart() {
//        //删除未完全创建成功的广告
//        log.info("[广告完成情况： getSuccessAdInfo() -> {} ]", getSuccessAdInfo());
//        List<Long> adList = getSuccessAdInfo().stream().filter(e -> e != null && e.getStatus() != null && e.getAdUnitId() != null && e.getStatus() != 901).map(AdInfo::getAdUnitId).collect(Collectors.toList());
//        log.info("[需要删除的广告：adList -> {} ]", adList);
//        List<Long> createList = new ArrayList<>();
//        getSuccessAdInfo().stream().filter(e -> e != null && e.getStatus() != null && e.getCreativeIds() != null && e.getStatus() != 901).forEach(e -> createList.addAll(e.getCreativeIds()));
//
//        log.info("[删除删除的创意：createList -> {} ]", createList);
//        RpcResult<JSONObject> jsonObjectRpcResult = adUnitService.updateAdUnitStatus(getAccessToken(), getAdTask().getDeliveryAccount(), adList, 3);
//        log.info("[删除广告组结果：jsonObjectRpcResult -> {} ]", jsonObjectRpcResult);
//        RpcResult<JSONObject> jsonObjectRpcResult1 = creativeService.updateCreativeStatus(getAccessToken(), getAdTask().getDeliveryAccount(), createList, 3);
//        log.info("[删除创意结果：jsonObjectRpcResult1 -> {} ]", jsonObjectRpcResult1);
//        //更新任务状态
//        AdTask adTask = getAdTask();
//        adTask.setTaskStatus(900);
//        AdTaskExecutionResult adTaskExecutionResult = resultMapper.selectById(adTask.getExecutionId());
//        if (adTaskExecutionResult == null) {
//            adTaskExecutionResult = new AdTaskExecutionResult();
//            adTaskExecutionResult.setId(adTask.getExecutionId());
//            adTaskExecutionResult.setAdTaskId(adTask.getId());
//        }
//        adTaskExecutionResult.setSuccessCount(getSuccessCount());
//        adTaskExecutionResult.setFailedCount(getTotalCount() - getSuccessCount());
//        adTaskExecutionResult.setUpdateTime(LocalDateTime.now());
//        adTaskExecutionResult.setExecutionTime(getAdTask().getExecutionTime());
//        resultMapper.updateById(adTaskExecutionResult);
//        adTaskMapper.updateById(adTask);
//
//    }
//
//    private String getAdSring() {
//        return String.valueOf(getDateByKey("adString"));
//    }
//
//    private void setAdSring(String adString) {
//        setDateByKey("adString", adString);
//    }
//
//    @Override
//    public void cacheExecutionStatus() {
//        redisService.setCacheObject(ABSTRACT_AD_TASK_EXECUTOR + getAdTask().getExecutionId(), this.data);
//        log.info("执行任务到阶段：status -> {} , data -> {}", getStatus(), this.data);
//    }
//
//    /**
//     * 字符串日期格式的计算
//     *
//     * @param smdate
//     * @param bdate
//     * @return
//     * @throws ParseException
//     */
//    public static int daysBetween(String smdate, String bdate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(sdf.parse(smdate));
//        long time1 = cal.getTimeInMillis();
//        cal.setTime(sdf.parse(bdate));
//        long time2 = cal.getTimeInMillis();
//        long between_days = (time2 - time1) / (1000 * 3600 * 24);
//        return Integer.parseInt(String.valueOf(between_days));
//    }
//
//    public static String plugDay(String date, int i) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dt = sdf.parse(date);
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(dt);
//        rightNow.add(Calendar.DAY_OF_YEAR, 10);//日期加10天
//        Date dt1 = rightNow.getTime();
//        String reStr = sdf.format(dt1);
//        return reStr;
//    }
//
//
//    private void handleBaseException(Exception e) {
//        e.printStackTrace();
//        log.info("异常：Exception -> {}", ExceptionUtil.getRootErrorMessage(e));
//        String key = "Execution:Result:Exception:Log" + getExecutionResult().getId();
//        JSONArray jsonArray = redisService.getBeanForCache(key, JSONArray.class);
//        if (jsonArray == null) {
//            jsonArray = new JSONArray();
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("devMessage", ExceptionUtil.getRootErrorMessage(e));
//        if (e instanceof KwaiException) {
//            jsonObject.put("code", ((KwaiException) e).getCode());
//            jsonObject.put("message", ((KwaiException) e).getDefaultMessage());
//        } else {
//            jsonObject.put("code", "9527");
//            jsonObject.put("message", "这是一个需要联系平台管理员的错误");
//        }
//        jsonArray.add(jsonObject);
//        redisService.cacheObject(key, jsonArray);
//        setBreak(true);
//    }
//
//
//}
