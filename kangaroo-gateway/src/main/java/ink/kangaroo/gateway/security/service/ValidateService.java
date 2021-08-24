package ink.kangaroo.gateway.security.service;

import ink.kangaroo.common.core.domain.R;
import ink.kangaroo.gateway.security.MobileCodeType;
import ink.kangaroo.gateway.security.SliderVerificationCodeType;
import ink.kangaroo.gateway.security.domain.SliderVerificationVo;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/7/19 13:31
 */
public interface ValidateService {

    /**
     * 验证滑块验证码：根据验证码的uuid以及Y轴偏移量验证滑块验证码是否正确
     *
     * @param uuid
     * @param percentage
     * @return
     */
//    R<Object> checkSlider(String uuid, String percentage, SliderVerificationCodeType sliderVerificationCodeType);

    R checkSlider(String uuid, int X, int Y, SliderVerificationCodeType type);

    /**
     * 获取手机验证码：根据手机验证码
     *
     * @param phone
     * @param type
     * @return
     */
//    R<String> getMobileCode(String phone, MobileCodeType type);

    /**
     * 获取手机验证码：根据手机验证码
     *
     * @param phone
     * @param timeout 过期时间 单位ms
     * @param type
     * @return
     */
//    R<String> getMobileCode(String phone, Long timeout, MobileCodeType type);

    /**
     * 获取滑块验证码 默认十分钟有效期
     *
     * @return
     */
    R<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type);

    /**
     * 获取滑块验证码
     *
     * @param type
     * @param timeout
     * @return
     */
    R<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type, Long timeout);

    /**
     * 验证手机验证码：根据手机,号码
     *
     * @param phone
     * @return
     */
//    R<String> checkMobileCode(String phone, String uuid, String code, MobileCodeType type);

    /**
     * 获取缓存中的手机验证码
     *
     * @param phone
     * @return
     */
//    R<String> getCacheMobileCode(String phone, String uuid, MobileCodeType type);

}
