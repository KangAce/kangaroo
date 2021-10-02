package ink.kangaroo.common.ekuaishou.model.creative.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/28 14:54
 */
@Data
public class UpdateCreativeStatusResult implements Serializable {

    /**
     * creative_ids
     */
    @JSONField(name = "creative_ids")
    private List<Long> creativeIds;
}
