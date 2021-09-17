package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:26
 */
@Data
public class Age implements Serializable {

    /**
     * 年龄最小限制
     * 年龄区间最小为18岁
     * min
     */
    @NotNull
    @JSONField(name = "min")
    private Integer min;

    /**
     * 年龄最大限制
     * 年龄区间最大为55岁，且年龄最大限制须大于等于年龄最小限制
     * max
     */
    @NotNull
    @JSONField(name = "max")
    private Integer max;

}
