package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:38
 */
@Data
public class IntelliExtend implements Serializable {
    /**
     * 开启智能扩量
     * 默认为0，为1时打开智能扩量，其他数值报错；关闭智能扩量时，以下三个字段必须为0
     * is_open
     */
    @JsonAlias("is_open")
    private Integer is_open;
    /**
     * 不可突破年龄
     * 默认为0（可突破/无需控制），定向age数据不为空时，可设为1（不可突破）
     * no_age_break
     */
    @JsonAlias("no_age_break")
    private Integer no_age_break;
    /**
     * 不可突破性别
     * 默认为0（可突破/无需控制），定向gender数据不为空时，可设为1（不可突破）
     * no_gender_break
     */
    @JsonAlias("no_gender_break")
    private Integer no_gender_break;
    /**
     * 不可突破地域
     * 默认为0（可突破/无需控制），定向region数据不为空时，可设为1（不可突破）。
     * no_area_break
     */
    @JsonAlias("no_area_break")
    private Integer no_area_break;
}
