package ink.kangaroo.common.ekuaishou.model.exception;

import ink.kangaroo.common.core.exception.base.BaseException;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/23 23:48
 */
public class KwaiException extends BaseException {
    public KwaiException(String code, Object[] args, String message) {
        super(null, code, args, message);
    }
}
