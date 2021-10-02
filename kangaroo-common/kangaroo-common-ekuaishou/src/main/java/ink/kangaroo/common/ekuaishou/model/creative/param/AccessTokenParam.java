package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 获取快手TOKEN参数
 *
 * @author Neely
 * @version 1.0
 * @date 2021/9/28
 */
@Data
public class AccessTokenParam {
    @JSONField(name = "app_id")
    private String appId;
    @JSONField(name = "secret")
    private String secret;
    @JSONField(name = "auth_code")
    private String authCode;

}
