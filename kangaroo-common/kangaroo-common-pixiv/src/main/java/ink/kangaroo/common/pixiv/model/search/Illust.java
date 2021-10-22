package ink.kangaroo.common.pixiv.model.search;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Illust {

    @JsonSetter("total")
    @JSONField(name = "total")
    private Integer total;

    @JsonSetter("data")
    @JSONField(name = "data")
    private List<Object> data;

    @JsonSetter("bookmarkRanges")
    @JSONField(name = "bookmarkRanges")
    private List<BookmarkRanges> bookmarkRanges;

}
