package ink.kangaroo.common.ekuaishou.model.advertiser;

import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * 获取广告账户信息
 * 请求接口：https://ad.e.kuaishou.com/rest/openapi/v1/advertiser/info
 * 请求方式：GET
 * 数据格式：JSON
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:57
 */
public class AdvertiserAccountInfoResult {
    /**
     * user_id：账户快手ID
     */
    @JsonSetter("user_id")
    private Long userId;
    /**
     * corporation_name：公司名称
     */
    @JsonSetter("corporation_name")
    private String corporationName;
    /**
     * user_name：快手昵称
     */
    @JsonSetter("user_name")
    private String userName;
    /**
     * industry_id：二级行业 id
     */
    @JsonSetter("industry_id")
    private Long industryId;
    /**
     * industry_name：二级行业名称
     */
    @JsonSetter("industry_name")
    private String industryName;
    /**
     * primary_industry_id：一级行业 id
     */
    @JsonSetter("primary_industry_id")
    private Long primaryIndustryId;
    /**
     * primary_industry_name：一级行业名称
     */
    @JsonSetter("primary_industry_name")
    private String primaryIndustryName;
}
