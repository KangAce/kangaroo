package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class BookmarkRanges {

    @JsonSetter("min")
    @JSONField(name = "min")
    private Integer min;

}
