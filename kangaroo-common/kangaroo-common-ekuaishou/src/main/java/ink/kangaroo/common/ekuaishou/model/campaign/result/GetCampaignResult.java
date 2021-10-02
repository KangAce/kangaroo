package ink.kangaroo.common.ekuaishou.model.campaign.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/17 13:35
 */
@Data
public class GetCampaignResult {
    @JSONField(name = "total_count")
    private int totalCount;
    @JSONField(name = "details")
    private List<CampaignDetails> details;
}
