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
public enum SliderVerificationCodeType {
    /**
     * 获取手机验证码 1
     */
    PLATFORM_MOBILE_CODE(1),
    /**
     * 获取登录验证码 2
     */
    PLATFORM_LOGIN(2),
    /**
     * 获取修改密码验证码 3
     */
    PLATFORM_CHANGE_PASSWORD(3),
    /**
     * 获取忘记密码验证码 4
     */
    PLATFORM_FORGET_PASSWORD(4),
    ;

    Integer type;
    public static SliderVerificationCodeType getByValue(Integer type){
        if (type==null){
            return null;
        }
        for (SliderVerificationCodeType value : values()) {
            if (type.equals(value.getType())){
                return value;
            }
        }
        return null;
    }
}
