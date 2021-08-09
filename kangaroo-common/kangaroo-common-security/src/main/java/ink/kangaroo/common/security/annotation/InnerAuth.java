package ink.kangaroo.common.security.annotation;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 1:07
 */
public @interface InnerAuth {
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}
