package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 广告组列表参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class UnitListParam {
    @JSONField(name = "advertiser_id")
    private Long advertiserId;

    @JSONField(name = "unit_id")
    private String unitId;

    @JSONField(name = "unit_name")
    private String unitName;

    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "page_size")
    private Integer pageSize;


}
