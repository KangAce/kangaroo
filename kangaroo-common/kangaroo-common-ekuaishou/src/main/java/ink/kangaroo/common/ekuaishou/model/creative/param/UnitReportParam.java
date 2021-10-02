package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 广告组日报表请求参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class UnitReportParam {
    @JSONField(name = "advertiser_id")
    private Long advertiserId;

    @JSONField(name = "start_date")
    private String startDate;

    @JSONField(name = "end_date")
    private String endDate;

    @JSONField(name = "unit_ids")
    private List<Long> unitIds;

    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "page_size")
    private Integer pageSize;


}
