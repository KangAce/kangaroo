package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 18:08
 */
@Data
public class UpdateCreativeStatusParam {
    /**
     * advertiser_id
     */
    @JSONField(name = "advertiser_id")
    private Long advertiserId;
    /**
     * advertiser_id
     */
    @JSONField(name = "creative_id")
    private Long creativeId;
    /**
     * 1.传入的广告组 id 不得重复，且至少有一个;传入的广告组 id 总数最多 20 个。2.put_status 为 3 时，会删除广告组，广告组下的所有程序化创意，创意
     * unit_ids
     */
    @JSONField(name = "creative_ids")
    private List<Long> creativeIds;
    /**
     * 1-投放、2-暂停、3-删除，传其他数字非法
     * put_status
     */
    @JSONField(name = "put_status")
    private Integer putStatus;
}
