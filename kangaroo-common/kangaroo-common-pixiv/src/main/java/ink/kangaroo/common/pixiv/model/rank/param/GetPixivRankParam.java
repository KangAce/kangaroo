package ink.kangaroo.common.pixiv.model.rank.param;

import com.alibaba.fastjson.annotation.JSONField;
import ink.kangaroo.common.pixiv.model.rank.PixivRankContent;
import ink.kangaroo.common.pixiv.model.rank.PixivRankMode;
import lombok.Data;

@Data
public class GetPixivRankParam {
    /**
     *
     */
    @JSONField(name = "mode")
    private PixivRankMode mode;
    @JSONField(name = "date")
    private String date;
    @JSONField(name = "content")
    private PixivRankContent content;
    @JSONField(name = "format")
    private String format;
    @JSONField(name = "p")
    private Integer pageNum;
}
