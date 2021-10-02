package ink.kangaroo.common.ekuaishou.model.campaign.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:18
 */
public class CreateCampaignResult implements Serializable {
    /**
     * 广告计划ID
     */
    @JSONField(name = "campaign_id")
    private Long campaignId;
}
