package ink.kangaroo.gateway.security.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shencaozuo.common.utils.ImageMergeInfo;
import com.shencaozuo.common.utils.ImageMergeUtils;
import com.shencaozuo.oauth2.config.MqConfig;
import com.shencaozuo.oauth2.vo.SliderVerificationVo;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.redis.enums.RedisKeyEnum;
import ink.kangaroo.common.redis.service.RedisService;
import ink.kangaroo.gateway.security.MobileCodeType;
import ink.kangaroo.gateway.security.SliderVerificationCodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/7/19 13:33
 */
@Slf4j
@Service
public class ValidateServiceImpl implements ValidateService {
    @Resource
    RedisService redisService;
    @Resource
    MqConfig mqConfig;
    @Resource
    ProducerBean producerBean;

    @Override
    public AjaxResult checkSlider(String uuid, int X, int Y, SliderVerificationCodeType type) {
        JSONObject message = new JSONObject();
        message.put("code", 2);
        message.put("massage", "验证不通过，请重试！");
        if (null == uuid || uuid.trim().length() < 1) {
            message.put("code", 0);
            message.put("massage", "请求参数错误:token为空！");
        }
        Map<String, Object> tokenObj = redisService.getCacheMap(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(type.getType().toString(), uuid));
        redisService.deleteObject(RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(String.valueOf(type.getType()), uuid));

        if (null == tokenObj) {
            message.put("code", -1);
            message.put("massage", "验证码超期，请重新请求！");
        } else {
            int sX = (Integer) tokenObj.get("X");
            int sY = (Integer) tokenObj.get("Y");
            if (sY != Y) {
                message.put("code", 0);
                message.put("massage", "请求参数错误:位置信息不正确！");
            } else {
                if (Math.abs(sX - X) <= 2) {
                    message.put("code", 1);
                    message.put("massage", "验证通过！");
                } else {
                    message.put("code", 2);
                    message.put("massage", "验证不通过，请重试！");
                }
            }
        }
        if (message.getInteger("code") == 1) {
            return AjaxResult.success().setData(true);
        } else {
            return AjaxResult.success().setData(false);
        }
    }

    @Override
    public AjaxResult getMobileCode(String phone, MobileCodeType type) {
        return getMobileCode(phone, 10 * 60 * 1000L, type);
    }

    @Override
    public RpcResult<String> getMobileCode(String phone, Long time, MobileCodeType type) {
        Message message = new Message();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNum", phone);
        int random = (int) (Math.random() * 999999);
        String code = String.format("%06d", random);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        jsonObject.put("uuid", uuid);
        jsonObject.put("code", code);
        jsonObject.put("type", type.getType());
        jsonObject.put("times", 0);
        JSONObject codeObject = new JSONObject();
        codeObject.put("code", code);
        codeObject.put("times", 0);
        codeObject.put("overtime", System.currentTimeMillis() + time);
        log.info("getMobileCode:key:{},value:{}", RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), codeObject.toJSONString());
        redisService.cacheObject(RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), codeObject.toJSONString(), time);
        message.setKey(uuid);
        message.setTag(mqConfig.getTag());
        message.setTopic(mqConfig.getTopic());
        message.setBody(jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
        producerBean.send(message);
        log.debug("uuid = {},code = {}, type = {}", uuid, code, type);

        return RpcResult.ok(uuid);
    }

    @Override
    public RpcResult<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type) {
        return this.getSliderVerificationCode(type, 60 * 10 * 1000L);
    }

    @Override
    public RpcResult<SliderVerificationVo> getSliderVerificationCode(SliderVerificationCodeType type, Long timeout) {
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            SliderVerificationVo sliderVerificationVo = new SliderVerificationVo();
            String key = RedisKeyEnum.SLIDER_VALIDATE_TOKEN.wrap2Key(String.valueOf(type.getType()), uuid);
            log.info("getSliderVerificationCode:key:{}", key);
            ImageMergeInfo slideImageInfo = ImageMergeUtils.getSlideImageInfo();
            String xPercent = String.valueOf(slideImageInfo.getXPercent());
            sliderVerificationVo.setOriCopyImage(slideImageInfo.getBackground());
            sliderVerificationVo.setNewImage(slideImageInfo.getTarget());
            sliderVerificationVo.setUuid(uuid);
            log.info("uuid:{},xPercent:{}", uuid, xPercent);
            //十一分钟失效
            log.info("getSliderVerificationCode:value:{}", xPercent);
            redisService.cacheObject(key, xPercent, timeout);
            log.debug("uuid = {},xPercent = {}, yPercent = {}", uuid, xPercent, slideImageInfo.getYPercent());
            return RpcResult.ok(sliderVerificationVo);
        } catch (Exception e) {
            log.error("【滑块验证码】获取错误={}", e.getMessage());
            return RpcResult.fail(e.getMessage());
        }
    }

    @Override
    public RpcResult<String> checkMobileCode(String phone, String uuid, String code, MobileCodeType type) {
        RpcResult<String> cacheMobileCode = this.getCacheMobileCode(phone, uuid, type);
        if (!cacheMobileCode.getCode().equals(ResultEnum.OK.getCode())) {
            return RpcResult.of(11011, "验证码无效:" + cacheMobileCode.getMsg());
        }
        String cacheCode = cacheMobileCode.getData();
        if (StringUtils.isEmpty(cacheCode)) {
            log.info("获取缓存验证码失败：phone：{}，uuid：{}，type：{}", phone, uuid, type.getType());
            return RpcResult.of(11012, "验证码错误");
        }
        if (!cacheCode.equals(code)) {
            log.info("验证码不相同：phone：{}，uuid：{}，type：{}", phone, uuid, type.getType());
            return RpcResult.of(11012, "验证码失败");
        }

        return RpcResult.ok("验证成功");
    }

    @Override
    public RpcResult<String> getCacheMobileCode(String phone, String uuid, MobileCodeType type) {
        String key = RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid);
        log.info("getCacheMobileCode:key:{}", key);
        String cacheCodeJson = redisService.getBeanForCache(key, String.class);
        log.info("getCacheMobileCode:value:{}", cacheCodeJson);
        JSONObject jsonObject = JSON.parseObject(cacheCodeJson);
        if (jsonObject == null) {
            return RpcResult.fail("验证码失效,请重新获取");
        }
        if (System.currentTimeMillis() > jsonObject.getLong("overtime")) {
            return RpcResult.fail("验证码失效,请重新获取!");
        }
        if (jsonObject.getInteger("times") > 2) {
            redisService.clearCacheForCacheKey(key);
        }
        jsonObject.put("times", jsonObject.getInteger("times") + 1);
        redisService.cacheObject(RedisKeyEnum.SMS_VALIDATE_TOKEN.wrap3Key(phone, String.valueOf(type.getType()), uuid), jsonObject.toJSONString(), 10 * 60 * 1000L);
        return RpcResult.ok(jsonObject.getString("code"));

        //需要可以使用三次
    }

}
