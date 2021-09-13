package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:39
 */
public class BehaviorInterest implements Serializable {

    /**
     * 行为定向
     * behavior与interest同时不传，则清空行为兴趣定向
     * behavior
     */
    @NotNull
    @JsonAlias("behavior")
    private Behavior behavior;

    /**
     * 兴趣定向
     * behavior与interest同时不传，则清空行为兴趣定向
     * interest
     */
    @JsonAlias("interest")
    private Interest interest;
}
