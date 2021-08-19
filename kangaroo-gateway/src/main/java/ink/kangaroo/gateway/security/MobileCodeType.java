package ink.kangaroo.gateway.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/7/19 14:46
 */
@Getter
@AllArgsConstructor
public enum MobileCodeType {
    /**
     * platform注册
     */
    PLATFORM_REGISTER(1),
    /**
     * platform修改密码
     */
    PLATFORM_CHANGE_PASSWORD(2),
    /**
     * platform登录
     */
    PLATFORM_LOGIN(3),
    /**
     * 主体注册
     */
    PLATFORM_ADD_ACCOUNT(4),
    /**
     * 忘记密码
     */
    OAUTH_FORGET_PASSWORD(5),
    ;

    Integer type;

    public static MobileCodeType getByValue(Integer type){
        if (type==null){
            return null;
        }
        for (MobileCodeType value : values()) {
            if (type.equals(value.getType())){
                return value;
            }
        }
        return null;
    }
}
