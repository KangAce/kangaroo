package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 除非接口变得否则请勿修改
 *
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
    @JSONField(name = "is_open")
    private Integer isOpen;
    /**
     * 不可突破年龄
     * 默认为0（可突破/无需控制），定向age数据不为空时，可设为1（不可突破）
     * no_age_break
     */
    @JSONField(name = "no_age_break")
    private Integer noAgeBreak;
    /**
     * 不可突破性别
     * 默认为0（可突破/无需控制），定向gender数据不为空时，可设为1（不可突破）
     * no_gender_break
     */
    @JSONField(name = "no_gender_break")
    private Integer noGenderBreak;
    /**
     * 不可突破地域
     * 默认为0（可突破/无需控制），定向region数据不为空时，可设为1（不可突破）。
     * no_area_break
     */
    @JSONField(name = "no_area_break")
    private Integer noAreaBreak;
}
