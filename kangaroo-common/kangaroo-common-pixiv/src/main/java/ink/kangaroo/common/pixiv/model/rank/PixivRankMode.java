package ink.kangaroo.common.pixiv.model.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum PixivRankMode {

    PIXIV_RANK_DAILY(0, "每日", "daily"),
    PIXIV_RANK_WEEKLY(1, "每周", "weekly"),
    PIXIV_RANK_MONTHLY(2, "每月", "monthly"),
    PIXIV_RANK_ROOKIE(3, "新人", "rookie"),
    PIXIV_RANK_ORIGINAL(4, "原创", "original"),
    PIXIV_RANK_male(5, "男生向", "male"),
    PIXIV_RANK_female(6, "女生向", "female"),

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


    public static PixivRankMode getById(Integer type) {
        if (type == null) {
            return null;
        }
        for (PixivRankMode value : values()) {
            if (type.equals(value.getId())) {
                return value;
            }
        }
        return null;
    }
    public static PixivRankMode getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PixivRankMode pixivRankMode : values()) {
            if (value.equals(pixivRankMode.getValue())) {
                return pixivRankMode;
            }
        }
        return null;
    }
}
