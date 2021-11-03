package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class TitleCaptionTranslation {

    @JsonSetter("workCaption")
    @JSONField(name = "workCaption")
    private Object workCaption;

    @JsonSetter("workTitle")
    @JSONField(name = "workTitle")
    private Object workTitle;

}
