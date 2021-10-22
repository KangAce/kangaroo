package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class ZoneConfig {

    @JsonSetter("footer")
    @JSONField(name = "footer")
    private Footer footer;

    @JsonSetter("header")
    @JSONField(name = "header")
    private Header header;

    @JsonSetter("infeed")
    @JSONField(name = "infeed")
    private Infeed infeed;

}
