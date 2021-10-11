package ink.kangaroo.common.core.web.domain;

import ink.kangaroo.common.core.enums.ResultEnums;
import ink.kangaroo.common.core.exception.base.BaseException;

import java.util.HashMap;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/2 13:45
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "message";

    private static final String SUCCESS = "success";

    private static final String CODE = "code";

    private static final String MODULES = "modules";

    public static final String DATA = "data";

    private static final String REDIRECT = "redirect";

    private static final String EMPTY = "";

    public static AjaxResult fail(Integer code,String message) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.putData(CODE, code);
        ajaxResult.putData(MESSAGE, message);
        return ajaxResult;
    }
    public static AjaxResult fail(BaseException e) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.putData(CODE, e.getCode());
        ajaxResult.putData(MODULES, e.getModule());
        ajaxResult.putData(MESSAGE, e.getDefaultMessage());
        return ajaxResult;
    }

    public static AjaxResult of(ResultEnums resultEnums) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.putData(CODE, resultEnums.getCode());
        ajaxResult.putData(MESSAGE, resultEnums.getMessage());
        return ajaxResult;
    }

    public boolean isSuccess() {
        return get(CODE) != null && "00000".equals(get(CODE));
    }

    public String getMessage() {
        if (get(MESSAGE) != null) {
            return (String) get(MESSAGE);
        }
        return EMPTY;
    }

    private AjaxResult() {
        super();
    }

    public static AjaxResult success() {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.put(SUCCESS, true);
        return ajaxResult;
    }

    public static AjaxResult success(String message) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.put(SUCCESS, true);
        ajaxResult.put(MESSAGE, message);
        return ajaxResult;
    }

    public static AjaxResult fail(String message) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.put(SUCCESS, false);
        ajaxResult.put(MESSAGE, message);
        return ajaxResult;
    }

    public static AjaxResult redirect(String url) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.put(REDIRECT, url);
        return ajaxResult;
    }

    public AjaxResult setData(Object data) {
        return putData(DATA, data);
    }

    public AjaxResult putData(String key, Object data) {
        this.put(key, data);
        return this;
    }

    public static AjaxResult createAjaxResult() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put(SUCCESS, true);
        return ajaxResult;
    }

    public static AjaxResult createAjaxResult(Object data) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.setData(data);
        return ajaxResult;
    }
}
