package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:47
 */
@Data
public class Keyword {


    /**
     * 关键词id
     * id与name需互相匹配
     * id
     */
    @NotNull
    @JSONField(name = "id")
    private Long id;

    /**
     * 关键词name
     * id与name需互相匹配
     * name
     */
    @NotNull
    @JSONField(name = "name")
    private String name;

}
