package ink.kangaroo.common.redis.enums;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/6/17 17:35
 */
public enum RedisKeyEnum {

    /**
     * account key name
     */
    IMAGE_SPACE_USE("image:space:use:%s:"),
    IMAGE_SPACE_TOTAL("image:space:total:%s:"),
    /**
     * platform项目Token  type:uuid
     */
    PLATFORM_TOKEN ("platform:token:%s"),
    /**
     * platform项目Token  type:uuid
     */
    PLATFORM_USERNAME_TOKEN ("platform:token:username:%s"),
    /**
     * 滑块验证码  type:uuid
     */
    SLIDER_VALIDATE_TOKEN("slider:validate:uuid:%s:%s"),
    /**
     * 短信验证码  phone:type:uuid
     */
    SMS_VALIDATE_TOKEN("sms:validate:uuid:%s:%s:%s"),
    /**
     * 解决消息队列消费幂等的Redis key
     * message queue Idempotent {队列类型:message key} 幂等
     */
    MESSAGE_QUEUE_IDEMPOTENT("message:queue:idempotent:%s:%s"),

    KEY_VALIDATE_IMG("key:validate:img:%s:%s"),
    KEY_VALIDATE_TPL("key:validate:tpl:%s:%s"),
    KEY_VALIDATE_TOKEN("key:validate:token:%s:%s"),

    ;
    private final String keyName;

    RedisKeyEnum(String keyName) {
        this.keyName = keyName;
    }

    public String keyName() {
        return keyName;
    }

    public String wrapKey(String str) {
        return String.format(keyName, str);
    }

    /**
     * key中有两个%s
     *
     * @param str1 第一个%s
     * @param str2 第二个%s
     * @return redis的key值
     */
    public String wrap2Key(String str1, String str2) {
        return String.format(keyName, str1, str2);
    }

    public String wrap3Key(String str1, String str2, String str3) {
        return String.format(keyName, str1, str2, str3);
    }
}
