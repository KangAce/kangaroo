package ink.kangaroo.common.ekuaishou.model.creative.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 18:26
 */
public class CreateCreativeResult {
    /**
     * 创意ID
     * <p>
     * creative_id
     */
    @JSONField(name = "creative_id")
    private Long creativeId;
}
