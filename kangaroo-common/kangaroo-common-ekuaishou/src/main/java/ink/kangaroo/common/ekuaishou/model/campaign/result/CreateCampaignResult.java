package ink.kangaroo.common.ekuaishou.model.campaign.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:18
 */
@Data
public class CreateCampaignResult implements Serializable {
    /**
     * 广告计划ID
     */
    @JSONField(name = "campaign_id")
    private Long campaignId;
}
