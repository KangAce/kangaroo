package ink.kangaroo.common.ekuaishou.model.advertiser;

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
public class GetAdvertiserAccountInfoParam {
    /**
     * advertiser_id：广告主ID	在获取access_token的时候返回
     */
    private Long advertiserId;
}
