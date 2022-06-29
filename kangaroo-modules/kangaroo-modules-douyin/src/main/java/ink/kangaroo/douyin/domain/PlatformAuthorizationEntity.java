package ink.kangaroo.douyin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import ink.kangaroo.common.core.annotation.Excel;
import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@Data
@Accessors(chain = true)
@TableName("platform_authorization")
public class PlatformAuthorizationEntity extends BaseEntity {

    /**
     * 授权主键
     */
    @Excel(name = "授权主键", cellType = Excel.ColumnType.NUMERIC)
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Long authId;
    /**
     * 授权用户唯一标识
     */
    @Excel(name = "open_id")
    @TableField("open_id")
    private String openId;

    /**
     * 授权名称
     */
    @Excel(name = "授权名称")
    @TableField("auth_name")
    private String authName;

    /**
     * 授权类型（平台） 1=抖音，2-西瓜，3-头条
     */
    @Excel(name = "授权类型（平台）")
    @TableField("auth_type")
    private String authType;


    /**
     * access_token接口调用凭证超时时间，单位（秒)
     */
    @Excel(name = "access_token")
    @TableField("access_token")
    private String accessToken;
    /**
     * refresh_token
     */
    @Excel(name = "refresh_token")
    @TableField("refresh_token")
    private String refreshToken;

    /**
     * 过期时间
     */
    @Excel(name = "expires_in")
    @TableField("expires_in")
    private String expiresIn;
    /**
     * 	refresh_token凭证超时时间，单位（秒)
     */
    @Excel(name = "refresh_expires_in")
    @TableField("refresh_expires_in")
    private String refreshExpiresIn;
    /**
     * 用户授权的作用域(Scope)，使用逗号（,）分隔，开放平台几乎几乎每个接口都需要特定的Scope。
     */
    @Excel(name = "scope")
    @TableField("scope")
    private String scope;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @TableField("status")
    private String status;

    /**
     * 是否有效（0正常 1过期）
     */
    @Excel(name = "是否有效", readConverterExp = "（0=正常,1=过期）")
    @TableField("open_id")
    private String isExpired;

}
