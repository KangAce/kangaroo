package ink.kangaroo.common.ekuaishou.model.token;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:52
 */
public class RefreshTokenResult {
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
}
