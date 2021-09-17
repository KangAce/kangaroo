package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:17
 */
public class DpaUnitParam {

    /**
     * 商品库ID
     * 当子计划类型为sdpa时必填
     * library_id
     */
    @JSONField(name = "library_id")
    private Long library_id;

    /**
     * 外部商品ID
     * 当子计划类型为sdpa时必填
     * dpa_unit_param
     */
    @JSONField(name = "outer_id")
    private String outer_id;

    /**
     * 商品链接类型
     * 0：app下载，1：H5跳转，2：Deeplink唤起，当计划类型为sdpa时必填
     * detail_unit_type
     */
    @JSONField(name = "detail_unit_type")
    private Integer detailUnitType;

    /**
     * 快手商品ID
     * 当计划子类型为sdpa时必填
     * productId
     */
    @JSONField(name = "productId")
    private String productId;

    /**
     * 点击跳转监控链接
     * dpa使用
     * click_url
     */
    @JSONField(name = "click_url")
    private String clickUrl;

    /**
     * actionbar点击跳转监控链接
     * dpa使用
     * actionbar_click_url
     */
    @JSONField(name = "actionbar_click_url")
    private String actionbarClickUrl;

    /**
     * 曝光监控链接
     * dpa使用
     * impression_url
     */
    @JSONField(name = "impression_url")
    private String impressionUrl;
}
