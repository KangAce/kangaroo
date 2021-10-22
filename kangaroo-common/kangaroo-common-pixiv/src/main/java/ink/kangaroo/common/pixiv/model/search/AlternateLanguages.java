package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class AlternateLanguages {

    @JsonSetter("ja")
    @JSONField(name = "ja")
    private String ja;

    @JsonSetter("en")
    @JSONField(name = "en")
    private String en;

}
