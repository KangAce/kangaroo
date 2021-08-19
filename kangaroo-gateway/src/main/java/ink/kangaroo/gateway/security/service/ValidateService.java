package ink.kangaroo.gateway.security.service;

import com.shencaozuo.common.RpcResult;
import com.shencaozuo.common.enums.MobileCodeType;
import com.shencaozuo.common.enums.SliderVerificationCodeType;
import com.shencaozuo.oauth2.vo.SliderVerificationVo;

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
    RpcResult<Object> checkSlider(String uuid, String percentage, SliderVerificationCodeType sliderVerificationCodeType);

    /**
     * 获取手机验证码：根据手机验证码
     *
     * @param phone
     * @param type
     * @return
     */
    RpcResult<String> getMobileCode(String phone, MobileCodeType type);

    /**
     * 获取手机验证码：根据手机验证码
     *
     * @param phone
     * @param timeout 过期时间 单位ms
     * @param type
     * @return
     */
    RpcResult<String> getMobileCode(String phone, Long timeout, MobileCodeType type);

    /**
     * 获取滑块验证码 默认十分钟有效期
     *
     * @return
     */
    RpcResult<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type);

    /**
     * 获取滑块验证码
     *
     * @param type
     * @param timeout
     * @return
     */
    RpcResult<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type, Long timeout);

    /**
     * 验证手机验证码：根据手机,号码
     *
     * @param phone
     * @return
     */
    RpcResult<String> checkMobileCode(String phone, String uuid, String code, MobileCodeType type);

    /**
     * 获取缓存中的手机验证码
     *
     * @param phone
     * @return
     */
    RpcResult<String> getCacheMobileCode(String phone, String uuid, MobileCodeType type);

}
