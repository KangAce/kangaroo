package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:21
 */
public class CreateAdUnitResult {
    /**
     * 广告组ID
     * unit_id
     */
    @JSONField(name = "unit_id")
    private Long unitId;
}
