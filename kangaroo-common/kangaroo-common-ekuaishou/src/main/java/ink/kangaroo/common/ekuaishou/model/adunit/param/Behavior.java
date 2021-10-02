package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 除非接口变得否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:46
 */
@Data
public class Behavior implements Serializable {


    /**
     * 行为定向关键词
     * 根据/rest/openapi/v1/tool/keyword/query接口获取
     * keyword
     */
    @JSONField(name = "keyword")
    private List<Keyword> keyword;

    /**
     * 行为定向 类目词
     * 根据/rest/openapi/v1/tool/label/behavior_interest接口获取。将行为类目id从最高层类目id开始，以“-”连接起来，假如有一个类目id为80202，父类目id为802，最高层类目id为8，则此时应该写"8-802-80202"；如果想全选最高层类目"8"底下的所有子类目，填"8"
     * label
     */
    @JSONField(name = "label")
    private List<String> label;

    /**
     * 在多少天内发生行为的用户
     * 0:7天；1：15天；2：30天；3：90天；4：180天
     * time_type
     */
    @NotNull
    @JSONField(name = "time_type")
    private Integer timeType;

    /**
     * 行为强度
     * 0：不限；1：高强度
     * strength_type
     */
    @NotNull
    @JSONField(name = "strength_type")
    private Integer strengthType;

    /**
     * 行为场景
     * 1：社区；2：APP；3:电商；4：推广
     * scene_type
     */
    @NotNull
    @JSONField(name = "scene_type")
    private List<Integer> sceneType;

}
