package ink.kangaroo.common.ekuaishou.model.token;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

/**
 * 获取token的参数
 * 利用授权码auth_code，请求快手服务器，获取access_token和refresh_token及当前账户的广告主ID。
 * 请求地址：https://ad.e.kuaishou.com/rest/openapi/oauth2/authorize/access_token
 * 请求方式：POST
 * 数据格式：JSON
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:46
 */
public class GetTokenResult {
    /**
     * access_token:用于验证权限的token
     */
    @JsonSetter("access_token")
    private String accessToken;
    /**
     * access_token_expires_in:access_token剩余有效时间，单位：秒
     */
    @JsonSetter("access_token_expires_in")
    private Long accessTokenExpiresIn;
    /**
     * refresh_token:用于获取新的access_token和refresh_token，并且刷新过期时间
     */
    @JsonSetter("refresh_token")
    private String refreshToken;
    /**
     * refresh_token_expires_in:access_token剩余有效时间，单位：秒
     */
    @JsonSetter("refresh_token_expires_in")
    private Long refreshTokenExpiresIn;
    /**
     * advertiser_id:广告主ID
     */
    @JsonSetter("advertiser_id")
    private Long advertiserId;
    /**
     * advertiser_ids:已授权账户所有的account_id
     */
    @JsonSetter("advertiser_ids")
    private List<Long> advertiserIds;
}
