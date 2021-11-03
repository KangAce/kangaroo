package ink.kangaroo.common.pixiv.model.discovery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum PixivDiscoveryCategory {
    /**
     *
     */
    PIXIV_CATEGORY_TOP(-1, "顶部", "top"),
    PIXIV_CATEGORY_NOVELS(0, "小说", "novels"),
    PIXIV_CATEGORY_USERS(1, "用户", "users"),
    PIXIV_CATEGORY_ARTWORKS(2, "漫画插画", "artworks"),

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


    public static PixivDiscoveryCategory getById(Integer type) {
        if (type == null) {
            return null;
        }
        for (PixivDiscoveryCategory value : values()) {
            if (type.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }

    public static PixivDiscoveryCategory getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PixivDiscoveryCategory pixivDiscoveryCategory : values()) {
            if (value.equals(pixivDiscoveryCategory.getValue())) {
                return pixivDiscoveryCategory;
            }
        }
        return null;
    }
}
