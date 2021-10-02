package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 广告创意列表参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class CreativeListParam {
    @JSONField(name = "advertiser_id")
    private Long advertiserId;

    @JSONField(name = "creative_id")
    private String creativeId;

    @JSONField(name = "creative_name")
    private String creativeName;

    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "page_size")
    private Integer pageSize;


}
