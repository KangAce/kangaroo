package ink.kangaroo.common.ekuaishou.model.campaign.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/17 13:35
 */
@Data
public class GetCampaignParam {

    /**
     * 必填	广告主 ID	在获取 access_token 的时候返回
     */
    @NotNull
    @JSONField(name = "advertiser_id")
    private Long advertiserId;
    /**
     * 可选	广告计划 ID	过滤筛选条件，若不传或传空则视为无限制条件
     */
    @JSONField(name = "campaign_id")
    private Long campaignId;
    /**
     * 可选	广告组 ID	过滤筛选条件，若不传或传空则视为无限制条件
     */
    @JSONField(name = "unit_id")
    private Long unitId;
    /**
     * 可选	广告组名称	精确查询
     */
    @JSONField(name = "unit_name")
    private String unitName;
    /**
     * []	可选	广告组 ID 集	不超过 100 个
     */
    @JSONField(name = "unit_ids")
    private List<Long> unitIds;
    /**
     * 可选	广告组状态	过滤筛选条件；-2：所有包含已删除 10：只包含已删除 不传：所有不包含已删除 其他值无效
     */
    @JSONField(name = "status")
    private Integer status;
    /**
     * 可选	开始时间	与 end_date 同时传或同时不传；过滤筛选条件，格式为"yyyy-MM-dd"，参数值对应 update_time 信息
     */
    @JSONField(name = "start_date")
    private String startDate;
    /**
     * 可选	结束时间	与 start_date 同时传或同时不传；过滤筛选条件，格式为"yyyy-MM-dd"，参数值对应 update_time 信息
     */
    @JSONField(name = "end_date")
    private String endDate;
    /**
     * 可选	按创建时间，还是更新时间进行筛选	1.如传入此字段时不传"start_date"，与"end_date"字段，则不根据时间筛选。2.传入"start_date"，与"end_date"字段，且此字段为 1 时，按照创建时间进行筛选。3.传入"start_date"，与"end_date"字段，此字段不传，或传值为 0 时，则按照更新时间进行筛选
     */
    @JSONField(name = "time_filter_type")
    private Integer timeFilterType;
    /**
     * 可选	请求的页码数	默认为 1
     */
    @JSONField(name = "page")
    private Integer page;
    /**
     * 可选	请求的每页行数	默认为 20
     */
    @JSONField(name = "page_size")
    private Integer pageSize;
}
