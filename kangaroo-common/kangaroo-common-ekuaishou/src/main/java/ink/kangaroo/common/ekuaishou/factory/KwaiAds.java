package ink.kangaroo.common.ekuaishou.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import ink.kangaroo.common.ekuaishou.model.adunit.param.CreateAdUnitParam;
import ink.kangaroo.common.ekuaishou.model.adunit.param.GetAdUnitInfoParam;
import ink.kangaroo.common.ekuaishou.model.adunit.result.AdUnitDetailsDataResult;
import ink.kangaroo.common.ekuaishou.model.base.BaseResult;
import ink.kangaroo.common.ekuaishou.model.campaign.param.CreateCampaignParam;
import ink.kangaroo.common.ekuaishou.model.campaign.param.GetCampaignParam;
import ink.kangaroo.common.ekuaishou.model.campaign.result.CreateCampaignResult;
import ink.kangaroo.common.ekuaishou.model.campaign.result.GetCampaignResult;
import ink.kangaroo.common.ekuaishou.model.creative.param.*;
import ink.kangaroo.common.ekuaishou.model.creative.result.CreateCreativeResult;
import ink.kangaroo.common.ekuaishou.model.creative.result.GetCreativeInfo2Result;
import ink.kangaroo.common.ekuaishou.model.creative.result.GetCreativeInfoResult;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/16 14:20
 */
@Data
public class KwaiAds {
    private static final String KWAI_URL = "https://ad.e.kuaishou.com/rest/openapi/";

    private static final KwaiAds INSTANCE = new KwaiAds();


    private String appIds;

    private String appsecurit;

    private String accessToken;

    /**
     * 获取广告信息
     *
     * @return
     */
    public BaseResult<JSONObject> getAdUnitInfo(GetAdUnitInfoParam getAdUnitInfoParam) {
        String path = "v1/ad_unit/list";
        return (BaseResult<JSONObject>) postRequest(path,getAdUnitInfoParam,BaseResult.class);
    }

    /**
     * 获取快手TOKEN
     *
     * @param param
     * @return
     */
    public BaseResult<JSONObject> getAccessToken(AccessTokenParam param) {
        String path = "oauth2/authorize/access_token";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 刷新快手TOKEN
     *
     * @param param
     * @return
     */
    public BaseResult<JSONObject> getRefreshAccessToken(RefreshAccessTokenParam param) {
        String path = "oauth2/authorize/refresh_token";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }


    /**
     * 获取快手用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getAdvertiser(AdvertiserParam param) throws Exception {
        String path = "v1/advertiser/info";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 获取罗盘快手用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getAdvertisers(AdvertiserParam param) throws Exception {
        String path = "gw/uc/v1/advertisers";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }


    /**
     * 获取广告计划列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getCampaignList(CampaignListParam param) throws Exception {
        String path = "v1/campaign/list";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 获取广告组列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getUnitList(UnitListParam param) throws Exception {
        String path = "v1/ad_unit/list";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 获取创意列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getCreativeList(CreativeListParam param) throws Exception {
        String path = "v1/creative/list";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 广告计划日报数据
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getCampaignReport(CampaignReportParam param) throws Exception {
        String path = "v1/report/campaign_report";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 广告组日报数据
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getUnitReport(UnitReportParam param) throws Exception {
        String path = "v1/report/unit_report";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }

    /**
     * 创意日报数据
     *
     * @param param
     * @return
     * @throws Exception
     */
    public BaseResult<JSONObject> getCreativeReport(CreativeReportParam param) throws Exception {
        String path = "v1/report/creative_report";
        return (BaseResult<JSONObject>) postRequest(path, param, JSONObject.class);
    }


    /**
     * 创建广告计划
     *
     * @param createCampaignParam
     * @return
     */
    public BaseResult<CreateCampaignResult> createCampaign(CreateCampaignParam createCampaignParam) throws Exception {
        String path = "v2/campaign/create";
        return (BaseResult<CreateCampaignResult>) postRequest(path, createCampaignParam, CreateCampaignResult.class);
    }

    /**
     * 获取广告计划
     *
     * @param getCampaignParam
     * @return
     */
    public BaseResult<GetCampaignResult> getCampaign(GetCampaignParam getCampaignParam) throws Exception {
        String path = "v1/campaign/list";
        return (BaseResult<GetCampaignResult>) postRequest(path, getCampaignParam, GetCampaignResult.class);
    }

    /**
     * 创建广告组
     *
     * @param createAdUnitParam
     * @return
     * @throws Exception
     */
    public BaseResult<CreateCampaignResult> createAdUnit(CreateAdUnitParam createAdUnitParam) throws Exception {
        String path = "v2/ad_unit/create";
        return (BaseResult<CreateCampaignResult>) postRequest(path, createAdUnitParam, CreateCampaignResult.class);
    }

    /**
     * 获取广告组
     *
     * @param getAdUnitInfoParam
     * @return
     * @throws Exception
     */
    public BaseResult<AdUnitDetailsDataResult> getAdUnit(GetAdUnitInfoParam getAdUnitInfoParam) throws Exception {
        String path = "v2/ad_unit/create";
        return (BaseResult<AdUnitDetailsDataResult>) postRequest(path, getAdUnitInfoParam, AdUnitDetailsDataResult.class);
    }

    /**
     * 创建创意
     *
     * @param createCreativeParam
     * @return
     * @throws Exception
     */
    public BaseResult<CreateCreativeResult> createCreative(CreateCreativeParam createCreativeParam) throws Exception {
        String path = "v2/creative/create";
        return (BaseResult<CreateCreativeResult>) postRequest(path, createCreativeParam, CreateCreativeResult.class);
    }

    /**
     * 获取创意
     *
     * @param getCreativeInfoParam
     * @return
     * @throws Exception
     */
    public BaseResult<GetCreativeInfoResult> getCreativeInfo(GetCreativeInfoParam getCreativeInfoParam) throws Exception {
        String path = "v1/creative/list";
        return (BaseResult<GetCreativeInfoResult>) postRequest(path, getCreativeInfoParam, GetCreativeInfoResult.class);
    }

    /**
     * 创建程序换创意2.0
     *
     * @param createCreative2Param
     * @return
     * @throws Exception
     */
    public BaseResult<CreateCampaignResult> createCreative2(CreateCreative2Param createCreative2Param) throws Exception {
        String path = "v2/creative/advanced/program/create";
        return (BaseResult<CreateCampaignResult>) postRequest(path, createCreative2Param, CreateCampaignResult.class);
    }

    /**
     * 获取程序化创意2.0
     *
     * @param getCreativeInfo2Param
     * @return
     * @throws Exception
     */
    public BaseResult<GetCreativeInfo2Result> getCreativeInfo2(GetCreativeInfo2Param getCreativeInfo2Param) throws Exception {
        String path = "v2/creative/advanced/program/list";
        return (BaseResult<GetCreativeInfo2Result>) postRequest(path, getCreativeInfo2Param, GetCreativeInfo2Result.class);
    }


    /**
     * @param path
     * @param clazz
     * @return
     */
    private BaseResult<?> postRequest(String path, Object param, Class<? extends Object> clazz) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Token", accessToken);
        headers.put("Content-Type", "application/json");
        var post = RestTemplateUtils.post(KWAI_URL + path, headers, param, BaseResult.class);
        BaseResult<?> body = post.getBody();
        assert body != null;
        return parse2(body, clazz);
    }

    private BaseResult<?> parse2(BaseResult<? extends Object> baseResult, Class clazz) {
        baseResult.setData(JSON.parseObject(JSON.toJSONString(baseResult.getData()), clazz));
        return baseResult;
    }

    private BaseResult<?> parse(String returnData, Class clazz) {
        BaseResult<Object> baseResult = JSON.parseObject(returnData, BaseResult.class);
        baseResult.setData(JSON.parseObject(JSON.toJSONString(baseResult.getData()), clazz));
        return baseResult;
    }
}
