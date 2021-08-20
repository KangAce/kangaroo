package ink.kangaroo.common.ekuaishou.model;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 14:46
 */
public class BaseResult<T> {
    /**
     * code:返回码
     */
    private Integer code;
    /**
     * message:返回信息
     */
    private Integer message;
    /**
     * data:struct
     */
    private T data;
}
