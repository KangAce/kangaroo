package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 发现漫画插画返回结果
 * https://www.pixiv.net/ajax/discovery/artworks?mode=all&limit=60&lang=zh
 */
@Data
public class DiscoveryResult {

    @JsonSetter("recommendedIllusts")
    @JSONField(name = "recommendedIllusts")
    private List<RecommendedIllusts> recommendedIllusts;

    @JsonSetter("tagTranslation")
    @JSONField(name = "tagTranslation")
    private Map tagTranslation;

    @JsonSetter("requests")
    @JSONField(name = "requests")
    private List<Object> requests;

    @JsonSetter("thumbnails")
    @JSONField(name = "thumbnails")
    private Thumbnails thumbnails;

    @JsonSetter("users")
    @JSONField(name = "users")
    private List<Users> users;

    @JsonSetter("illustSeries")
    @JSONField(name = "illustSeries")
    private List<Object> illustSeries;

}
