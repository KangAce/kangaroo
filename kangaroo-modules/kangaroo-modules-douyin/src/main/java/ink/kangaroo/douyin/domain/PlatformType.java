package ink.kangaroo.douyin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@AllArgsConstructor
public enum PlatformType {
    DOUYIN("抖音", 1),
    XIGUA("西瓜", 2),
    TOUTIAO("头条", 3),

    ;
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer code;

    public static PlatformType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PlatformType value : values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }

    public static PlatformType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (PlatformType pixivCategory : values()) {
            if (name.equals(pixivCategory.getName())) {
                return pixivCategory;
            }
        }
        return null;
    }
}
