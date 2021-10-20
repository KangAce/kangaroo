package ink.kangaroo.common.pixiv.model.illust;

import lombok.Data;

@Data
public class GetPixivIllustPageParam {
    private String illustId;
    private String lang = "zh";
}
