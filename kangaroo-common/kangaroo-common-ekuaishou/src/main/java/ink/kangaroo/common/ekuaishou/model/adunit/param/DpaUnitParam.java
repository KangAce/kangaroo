package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.fasterxml.jackson.annotation.JsonAlias;

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
    @JsonAlias("library_id")
    private Long library_id;

    /**
     * 外部商品ID
     * 当子计划类型为sdpa时必填
     * dpa_unit_param
     */
    @JsonAlias("outer_id")
    private String outer_id;

    /**
     * 商品链接类型
     * 0：app下载，1：H5跳转，2：Deeplink唤起，当计划类型为sdpa时必填
     * detail_unit_type
     */
    @JsonAlias("detail_unit_type")
    private Integer detailUnitType;

    /**
     * 快手商品ID
     * 当计划子类型为sdpa时必填
     * productId
     */
    @JsonAlias("productId")
    private String productId;

    /**
     * 点击跳转监控链接
     * dpa使用
     * click_url
     */
    @JsonAlias("click_url")
    private String clickUrl;

    /**
     * 	actionbar点击跳转监控链接
     * dpa使用
     * actionbar_click_url
     */
    @JsonAlias("actionbar_click_url")
    private String actionbarClickUrl;

    /**
     * 曝光监控链接
     * dpa使用
     * impression_url
     */
    @JsonAlias("impression_url")
    private String impressionUrl;
}
