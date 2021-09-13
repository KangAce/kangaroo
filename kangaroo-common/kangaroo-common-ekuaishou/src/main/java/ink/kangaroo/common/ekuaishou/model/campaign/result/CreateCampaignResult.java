package ink.kangaroo.common.ekuaishou.model.campaign.result;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:18
 */
public class CreateCampaignResult implements Serializable {
    /**
     * 广告计划ID
     */
    @JsonAlias("campaign_id")
    private Long campaignId;
}
