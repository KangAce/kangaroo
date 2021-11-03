package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class RecommendedIllusts {

    @JsonSetter("recommendScore")
    @JSONField(name = "recommendScore")
    private Integer recommendScore;

    @JsonSetter("illustId")
    @JSONField(name = "illustId")
    private String illustId;

    @JsonSetter("recommendMethods")
    @JSONField(name = "recommendMethods")
    private List<String> recommendMethods;

    @JsonSetter("recommendSeedIllustIds")
    @JSONField(name = "recommendSeedIllustIds")
    private List<Object> recommendSeedIllustIds;

}
