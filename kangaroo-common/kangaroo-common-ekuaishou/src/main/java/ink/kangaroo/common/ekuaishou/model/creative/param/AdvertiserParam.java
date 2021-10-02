package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 快手用户信息参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class AdvertiserParam {
    @JSONField(name = "advertiser_id")
    private Long advertiserId;

}
