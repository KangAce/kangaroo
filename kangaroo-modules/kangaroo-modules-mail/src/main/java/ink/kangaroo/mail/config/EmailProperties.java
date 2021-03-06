package ink.kangaroo.mail.config;

import ink.kangaroo.common.core.properties.PropertyEnum;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 13:42
 */
public enum EmailProperties implements PropertyEnum{

    /**
     * Email sender host
     */
    HOST("email_host", String.class, "smtp.126.com"),

    /**
     * Email sender protocol
     */
    PROTOCOL("email_protocol", String.class, "smtp"),

    /**
     * SSL port
     */
    SSL_PORT("email_ssl_port", Integer.class, "465"),

    /**
     * Email Sender username
     */
    USERNAME("email_username", String.class, "moretribes@126.com"),

    /**
     * Email Sender password
     */
    PASSWORD("email_password", String.class, "KLKCRTLICOVGQMTC"),

    /**
     * Email Sender name
     */
    FROM_NAME("email_from_name", String.class, "Kangaroo"),

    /**
     * Is enabled email sender
     */
    ENABLED("email_enabled", Boolean.class, "false");

    private final String value;

    private final Class<?> type;

    private final String defaultValue;

    EmailProperties(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        if (!PropertyEnum.isSupportedType(type)) {
            throw new IllegalArgumentException("Unsupported blog property type: " + type);
        }

        this.value = value;
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }

    @Override
    public String getValue() {
        return value;
}}
