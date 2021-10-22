package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class SearchPixivResult {

    @JsonSetter("relatedTags")
    @JSONField(name = "relatedTags")
    private List<Object> relatedTags;

    @JsonSetter("extraData")
    @JSONField(name = "extraData")
    private ExtraData extraData;

    @JsonSetter("tagTranslation")
    @JSONField(name = "tagTranslation")
    private List<Object> tagTranslation;

    @JsonSetter("zoneConfig")
    @JSONField(name = "zoneConfig")
    private ZoneConfig zoneConfig;

    @JsonSetter("illust")
    @JSONField(name = "illust")
    private Illust illust;

    @JsonSetter("popular")
    @JSONField(name = "popular")
    private Popular popular;
}
