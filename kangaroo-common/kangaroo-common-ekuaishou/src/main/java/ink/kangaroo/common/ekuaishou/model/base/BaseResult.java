package ink.kangaroo.common.ekuaishou.model.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:09
 */
@Data
public class BaseResult<T> implements Serializable {
    /**
     * code:返回码
     */
    @JSONField(name = "code")
    private Integer code;
    /**
     * message:返回信息
     */
    @JSONField(name = "message")
    private String message;
    /**
     * data:struct
     */
    @JSONField(name = "data")
    private T data;


}
