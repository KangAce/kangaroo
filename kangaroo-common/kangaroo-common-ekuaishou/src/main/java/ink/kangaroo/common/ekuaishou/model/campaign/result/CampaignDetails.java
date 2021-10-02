package ink.kangaroo.common.ekuaishou.model.campaign.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:18
 */
@Data
public class CampaignDetails {

    @JSONField(name = "details")
    private Integer status;
    @JSONField(name = "campaign_id")
    private Long campaignId;
    @JSONField(name = "campaign_name")
    private String campaignName;
    @JSONField(name = "put_status")
    private Integer putStatus;
    @JSONField(name = "create_channel")
    private Integer createChannel;
    @JSONField(name = "day_budget")
    private Integer dayBudget;
    @JSONField(name = "day_budget_schedule")
    private List<String> dayBudgetSchedule;
    @JSONField(name = "campaign_type")
    private Integer campaignType;
    @JSONField(name = "campaign_sub_type")
    private Integer campaignSubType;
    @JSONField(name = "create_time")
    private Date createTime;
    @JSONField(name = "update_time")
    private Date updateTime;

}