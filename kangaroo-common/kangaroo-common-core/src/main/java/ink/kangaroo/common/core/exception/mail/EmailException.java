package ink.kangaroo.common.core.exception.mail;

import ink.kangaroo.common.core.exception.base.BaseException;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/9 11:22
 */
public class EmailException extends BaseException {
    public EmailException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }

    public EmailException(String module, String code, Object[] args) {
        super(module, code, args);
    }

    public EmailException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public EmailException(String code, Object[] args) {
        super(code, args);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailException(String defaultMessage) {
        super(defaultMessage);
    }

}
