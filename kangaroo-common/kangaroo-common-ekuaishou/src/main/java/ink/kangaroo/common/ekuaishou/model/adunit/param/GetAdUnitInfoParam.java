package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/15 17:19
 */
@Data
public class GetAdUnitInfoParam implements Serializable {

    /**
     * 必填	广告主 ID	在获取 access_token 的时候返回
     * <p>
     * advertiser_id;
     */
    @JSONField(name = "advertiser_id")
    private long advertiserId;
    /**
     * 可选	广告计划 ID	过滤筛选条件，若不传或传空则视为无限制条件
     * <p>
     * campaign_id;
     */
     @JSONField(name = "campaign_id")
    private long campaignId;
    /**
     * 可选	广告计划类型	过滤筛选条件；2 - 提升应用安装;3 - 获取电商下单;4 - 推广品牌活动;5 - 收集销售线索;
     * <p>
     * campaign_type;
     */
     @JSONField(name = "campaign_type")
    private int campaignType;
    /**
     * 可选	广告组 ID	过滤筛选条件，若不传或传空则视为无限制条件
     * <p>
     * unit_id;
     */
     @JSONField(name = "unit_id")
    private Long unitId;
    /**
     * 可选	广告组名称	过滤筛选条件，支持模糊搜索&精确查询
     * <p>
     * unit_name;
     */
     @JSONField(name = "unit_name")
    private String unitName;
    /**
     * []	可选	广告组 ID 集	不超过 100 个
     * <p>
     * unit_ids;
     */
     @JSONField(name = "unit_ids")
    private List<Long> unitIds;
    /**
     * 可选	广告组状态	过滤筛选条件；-1：不限，1：计划已暂停，3：计划超预算，6：余额不足，11：审核中，12：审核未通过，14：已结束，15：已暂停，17：组超预算，19：未达投放时间，20：有效-2，22：不在投放时段。所有包含已删除 10：只包含已删除不传：所有不包含已删除 其他值无效
     * <p>
     * status;
     */
     @JSONField(name = "status")
    private int status;
    /**
     * 可选	应用名称	过滤筛选条件，支持模糊搜索
     * <p>
     * app_name;
     */
     @JSONField(name = "app_name")
    private String appName;
    /**
     * 可选	开始时间	与 end_date 同时传或同时不传；过滤筛选条件，格式为"yyyy-MM-dd"，参数值对应 update_time 信息
     * <p>
     * start_date;
     */
     @JSONField(name = "start_date")
    private String startDate;
    /**
     * 可选	结束时间	与 start_date 同时传或同时不传；过滤筛选条件，格式为"yyyy-MM-dd"，参数值对应 update_time 信息
     * <p>
     * end_date;
     */
     @JSONField(name = "end_date")
    private String endDate;
    /**
     * 可选	按创建时间或者更新时间进行筛选	1.如传入此字段时不传"start_date"，与"end_date"字段，则不根据时间筛选。2.传入"start_date"，与"end_date"字段，且此字段为 1 时，按照创建时间进行筛选。3.传入"start_date"，与"end_date"字段，此字段不传，或传值为 0 时，则按照更新时间进行筛选
     * <p>
     * time_filter_type;
     */
     @JSONField(name = "time_filter_type")
    private int timeFilterType;
    /**
     * 可选	请求的页码数	默认为 1
     * <p>
     * page;
     */
     @JSONField(name = "page")
    private int page;
    /**
     * 可选	请求的每页行数	默认为 20
     * <p>
     * page_size;
     */
     @JSONField(name = "page_size")
    private int pageSize;


}
