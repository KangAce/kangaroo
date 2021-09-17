package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:44
 */
@Data
public class Interest implements Serializable {
    /**
     * 兴趣定向类目词
     * 根据/rest/openapi/v1/tool/label/behavior_interest接口获取。将兴趣类目id从最高层类目id开始，以“-”连接起来，假如有一个类目id为80202，父类目id为802，最高层类目id为8，则此时应该写"8-802-80202"；如果想全选最高层类目"8"底下的所有子类目，填"8"
     * label
     */
    @JSONField(name = "label")
    private List<String> label;
    /**
     * 兴趣标签强度
     * 0：不限 1：高强度
     * strength_type
     */
    @NotNull
    @JSONField(name = "strength_type")
    private Integer strengthType;
}
