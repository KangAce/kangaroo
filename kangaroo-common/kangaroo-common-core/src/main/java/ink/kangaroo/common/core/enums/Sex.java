package ink.kangaroo.common.core.enums;

/**
 * @author Kang
 * @date 2020/10/30 17:44
 */
public enum Sex implements ValueEnum<Integer> {

    FEMALE(0),
    MALE(1),
    UNKNOW(-1),
    ;
    private final Integer value;

    Sex(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
