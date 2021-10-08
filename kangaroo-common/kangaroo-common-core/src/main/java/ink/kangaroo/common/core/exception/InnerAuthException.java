package ink.kangaroo.common.core.exception;

import ink.kangaroo.common.core.exception.base.BaseException;

/**
 * 内部认证异常
 *
 * @author kangaroo
 */
public class InnerAuthException extends BaseException {
    private static final long serialVersionUID = 1L;

    public InnerAuthException(String message) {
        super(message);
    }
}
