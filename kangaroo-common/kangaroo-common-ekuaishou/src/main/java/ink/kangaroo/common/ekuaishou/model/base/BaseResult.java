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

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "data")
    private T data;
}
