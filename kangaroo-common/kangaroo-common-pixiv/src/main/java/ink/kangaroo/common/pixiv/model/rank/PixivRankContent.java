package ink.kangaroo.common.pixiv.model.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum PixivRankContent {

    PIXIV_RANK_ALL(0, "全部", "all"),
    PIXIV_RANK_ILLUST(0, "插画", "illust"),

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


    public static PixivRankContent getByValue(Integer type) {
        if (type == null) {
            return null;
        }
        for (PixivRankContent value : values()) {
            if (type.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }
    public static PixivRankContent getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PixivRankContent pixivRankContent : values()) {
            if (value.equals(pixivRankContent.getValue())) {
                return pixivRankContent;
            }
        }
        return null;
    }
}
