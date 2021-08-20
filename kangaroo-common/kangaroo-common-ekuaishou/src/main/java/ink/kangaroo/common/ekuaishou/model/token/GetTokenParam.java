package ink.kangaroo.common.ekuaishou.model.token;

import org.springframework.core.annotation.AliasFor;

/**
 * 获取token的参数
 * 利用授权码auth_code，请求快手服务器，获取access_token和refresh_token及当前账户的广告主ID。
 * 请求地址：https://ad.e.kuaishou.com/rest/openapi/oauth2/authorize/access_token
 * 请求方式：POST
 * 数据格式：JSON
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:41
 */
public class GetTokenParam {
    /**
     * app_id:申请应用后快手返回的app_id
     */
    private Long appId;
    /**
     * secret:申请应用后快手返回的secret
     */
    private String secret;
    /**
     * auth_code:授权时返回的auth_code
     */
    private String authCode;

}
