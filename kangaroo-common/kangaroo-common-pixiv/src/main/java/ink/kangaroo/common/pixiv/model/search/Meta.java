package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Meta {

    @JsonSetter("alternateLanguages")
    @JSONField(name = "alternateLanguages")
    private AlternateLanguages alternateLanguages;

    @JsonSetter("description")
    @JSONField(name = "description")
    private String description;

    @JsonSetter("descriptionHeader")
    @JSONField(name = "descriptionHeader")
    private String descriptionHeader;

    @JsonSetter("canonical")
    @JSONField(name = "canonical")
    private String canonical;

    @JsonSetter("title")
    @JSONField(name = "title")
    private String title;

}
