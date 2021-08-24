package ink.kangaroo.gateway.security.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/7/16 17:34
 */
@Data
@ApiModel("滑块验证码")
public class SliderVerificationVo implements Serializable {

    private String uuid;
    private String newImage;
    @ApiModelProperty("背景图")
    private String oriCopyImage;

}
