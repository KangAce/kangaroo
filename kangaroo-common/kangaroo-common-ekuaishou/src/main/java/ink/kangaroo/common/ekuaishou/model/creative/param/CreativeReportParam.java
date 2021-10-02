package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class CreativeReportParam {
    @JSONField(name = "advertiser_id")
    private Long advertiserId;

    @JSONField(name = "start_date")
    private String startDate;

    @JSONField(name = "end_date")
    private String endDate;

    @JSONField(name = "creative_ids")
    private List<Long> creativeIds;

    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "page_size")
    private Integer pageSize;


}
