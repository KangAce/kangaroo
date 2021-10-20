package ink.kangaroo.common.pixiv.model.illust;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GetPixivIllustDetailParam {
    @JSONField(name = "illust_id")
    private String illustId;
}
