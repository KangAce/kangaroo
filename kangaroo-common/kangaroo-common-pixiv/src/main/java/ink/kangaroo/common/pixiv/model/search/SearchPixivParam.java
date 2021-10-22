package ink.kangaroo.common.pixiv.model.search;

import ink.kangaroo.common.pixiv.model.PixivCategory;
import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/10/21 15:13
 */
@Data
public class SearchPixivParam {
    private String keyword;
    private PixivCategory category;
}
