package ink.kangaroo.common.ekuaishou.model.base;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:09
 */
@Data
public class BaseResult<T>  implements Serializable {

    @JsonAlias("code")
    private Integer code;
    @JsonAlias("message")
    private String message;
    @JsonAlias("data")
    private T data;
    @JsonAlias("campaign_id")
    private Long campaignId;
}
