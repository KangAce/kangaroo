package ink.kangaroo.trying.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("推送订单信息")
@Data
@ToString
public class PushOrderInfoVo extends OrderInfoVo {

    private static final long serialVersionUID = 6947302275765705712L;

    @ApiModelProperty("旧的订单状态")
    @JSONField(name = "old_order_status")
    @JsonProperty(value = "old_order_status")
    private Byte oldOrderStatus;

    @Override
    public String toString() {
        return super.toString() + oldOrderStatus;
    }

}
