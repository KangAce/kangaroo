package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 除非接口变得否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:39
 */
@Data
public class BehaviorInterest implements Serializable {

    /**
     * 行为定向
     * behavior与interest同时不传，则清空行为兴趣定向
     * behavior
     */
    @NotNull
    @JSONField(name = "behavior")
    private Behavior behavior;

    /**
     * 兴趣定向
     * behavior与interest同时不传，则清空行为兴趣定向
     * interest
     */
    @JSONField(name = "interest")
    private Interest interest;
}
