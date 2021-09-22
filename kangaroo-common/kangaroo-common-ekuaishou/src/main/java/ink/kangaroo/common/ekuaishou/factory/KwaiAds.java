package ink.kangaroo.common.ekuaishou.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.utils.RestTemplateUtils;
import ink.kangaroo.common.ekuaishou.model.adunit.param.GetAdUnitInfoParam;
import ink.kangaroo.common.ekuaishou.model.base.BaseResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/16 14:20
 */
public class KwaiAds {
    private static final String KWAI_URL = "https://ad.e.kuaishou.com/rest/openapi/";

    private static final KwaiAds INSTANCE = new KwaiAds();

    private String accessToken;

    /**
     * 获取广告信息
     *
     * @return
     */
    public JSONObject getAdUnitInfo(GetAdUnitInfoParam getAdUnitInfoParam) throws Exception {
        String path = "v1/ad_unit/list";
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Access-Token", accessToken);
        ResponseEntity<BaseResult> post = RestTemplateUtils.post(KWAI_URL + path, headers, getAdUnitInfoParam, BaseResult.class);
        String returnData = null;
        if (returnData != null) {
            JSONObject returnObject = JSON.parseObject(returnData);
            if (returnObject == null || returnObject.get("code") == null || returnObject.get("data") == null
                    || returnObject.getInteger("code") != 0) {
                return null;
            }
            return returnObject.getJSONObject("data");
        } else {
            return null;
        }
    }
}
