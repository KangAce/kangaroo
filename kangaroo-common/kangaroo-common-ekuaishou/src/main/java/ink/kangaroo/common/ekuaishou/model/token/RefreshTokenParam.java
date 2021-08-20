package ink.kangaroo.common.ekuaishou.model.token;

/**
 * 刷新tonken
 * 请求快手服务器，刷新access_token和refresh_token及token过期时间。 请求地址：https://ad.e.kuaishou.com/rest/openapi/oauth2/authorize/refresh_token
 * 请求方式：POST
 * 数据格式：JSON
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:50
 */
public class RefreshTokenParam {

    /**
     * app_id:申请应用后快手返回的app_id
     */
    private Long appId;
    /**
     * secret:申请应用后快手返回的secret
     */
    private String secret;
    /**
     * refresh_token:最近一次快手返回的refresh_token
     */
    private String refreshToken;

}
