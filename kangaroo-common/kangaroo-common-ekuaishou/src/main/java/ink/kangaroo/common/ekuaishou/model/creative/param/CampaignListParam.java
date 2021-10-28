package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 广告计划列表参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class CampaignListParam {

    @JSONField(name = "advertiser_id")
    private Long advertiserId;

    @JSONField(name = "campaign_id")
    private String campaignId;

    @JSONField(name = "campaign_name")
    private String campaignName;

    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "page_size")
    private Integer pageSize;

}
