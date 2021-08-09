package ink.kangaroo.system.api.domain;

import ink.kangaroo.common.core.web.domain.BaseEntity;

/**
 * 第三方登录信息表
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 1:14
 */
public class SysUserAuth extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 登录信息主键
     */
    private Long id;
    /**
     * User ID
     */
    private Long userId;
    /**
     * 平台类型：微信小程序登录，github登录，qq登录
     */
    private String appType;
    /**
     * appId
     */
    private String appId;
    /**
     * 用户在开放平台的唯一标识符 有可能为null 据说是如果没有绑定开放平台就会过去不到，但是我绑定了还是获取不到，可能是延迟，暂时忽略，在此情景下，无论如何都要允许为空
     */
    private String unionId;
    /**
     * 唯一标识 openId
     */
    private String openId;
    /**
     * OAuth Access Token. session_key
     * 动态令牌
     */
    private String OAuthAccessToken;
    /**
     * OAuth Refresh Token.
     * 刷新令牌
     */
    private String OAuthRefreshToken;
    /**
     * 令牌过期时间
     */
    private String OAuthExpires;
}
