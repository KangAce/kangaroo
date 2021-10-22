package ink.kangaroo.common.pixiv.model;

import ink.kangaroo.common.pixiv.model.rank.PixivRankMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/10/21 15:16
 */
@AllArgsConstructor
public enum PixivCategory {
    /**
     *
     */
    PIXIV_CATEGORY_TOP(-1, "顶部", "top"),
    PIXIV_CATEGORY_NOVELS(0, "小说", "novels"),
    PIXIV_CATEGORY_ILLUSTRATIONS(1, "插画", "illustrations"),
    PIXIV_CATEGORY_MANGA(2, "漫画", "manga"),

    ;
    @Setter
    @Getter
    private Integer id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String value;


    public static PixivCategory getById(Integer type) {
        if (type == null) {
            return null;
        }
        for (PixivCategory value : values()) {
            if (type.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }
    public static PixivCategory getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PixivCategory pixivCategory : values()) {
            if (value.equals(pixivCategory.getValue())) {
                return pixivCategory;
            }
        }
        return null;
    }
}
