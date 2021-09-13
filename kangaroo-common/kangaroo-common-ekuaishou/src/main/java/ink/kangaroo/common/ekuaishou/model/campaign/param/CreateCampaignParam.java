package ink.kangaroo.common.ekuaishou.model.campaign.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:07
 */
@Data
public class CreateCampaignParam  implements Serializable {

    /**
     * 	广告主ID
     * 	在获取access_token的时候返回
     * 	advertiser_id
     */
    @JsonAlias("advertiser_id")
    private Long advertiserId;
    /**
     * 	广告计划名称
     * 	长度为1-100个字符，计划名称不能重复
     * 	campaign_name
     */

    @JsonAlias("campaign_name")
    private String campaignName;
    /**
     * 	计划类型
     * 	2：提升应用安装 3：获取电商下单 4：推广品牌活动 5：收集销售线索 7：提高应用活跃 9：商品库推广 13：小店商品推广 14：直播推广
     * 	type
     */
    @JsonAlias("type")
    private Integer type;
    /**
     * 	商品广告类型
     * 	5：SDPA 4:DPA 当type为9时，sub_type必为4、5两者之一。（目前只支持类型5：SDPA）
     * 	sub_type
     */
    @JsonAlias("sub_type")
    private Integer subType;
    /**
     * 	单日预算金额
     * 	单位：厘，指定0表示预算不限，默认为0；不小于500元，不超过100000000元，仅支持输入数字；修改预算不得低于该计划当日花费的120%，与day_budget_schedule不能同时传，不能低于该计划下任一广告组出价
     * 	day_budget
     */
    @JsonAlias("day_budget")
    private Long dayBudget;
    /**
     * 	分日预算
     * 	单位：厘，指定0表示预算不限，默认为0；每天不小于500元，不超过100000000元，仅支持输入数字；修改预算不得低于该计划当日花费的120%，与day_budget不能同时传，均不能低于该计划下任一广告组出价
     * 	day_budget_schedule
     */
    @JsonAlias("day_budget_schedule")
    private List<Long> dayBudgetSchedule;
}
