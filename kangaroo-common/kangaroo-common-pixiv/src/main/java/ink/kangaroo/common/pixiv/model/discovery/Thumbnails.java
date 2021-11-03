package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Thumbnails {

    @JsonSetter("novelSeries")
    @JSONField(name = "novelSeries")
    private List<Object> novelSeries;

    @JsonSetter("illust")
    @JSONField(name = "illust")
    private List<Illust> illust;

    @JsonSetter("novelDraft")
    @JSONField(name = "novelDraft")
    private List<Object> novelDraft;

    @JsonSetter("novel")
    @JSONField(name = "novel")
    private List<Novel> novel;

}
