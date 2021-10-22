package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Popular {

    @JsonSetter("permanent")
    @JSONField(name = "permanent")
    private List<Object> permanent;

    @JsonSetter("recent")
    @JSONField(name = "recent")
    private List<Object> recent;

}
