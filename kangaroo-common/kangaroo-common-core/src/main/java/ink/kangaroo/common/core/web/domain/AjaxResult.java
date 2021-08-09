package ink.kangaroo.common.core.web.domain;

import ink.kangaroo.common.core.exception.BaseException;

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

    private static final String DATA = "data";

    private static final String REDIRECT = "redirect";

    private static final String EMPTY = "";

    public static AjaxResult fail(BaseException e) {
        AjaxResult ajaxResult = createAjaxResult();
        ajaxResult.putData(CODE, e.getCode());
        ajaxResult.putData(MODULES, e.getModule());
        ajaxResult.putData(MESSAGE, e.getDefaultMessage());
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

    public AjaxResult success() {
        this.put(SUCCESS, true);
        return this;
    }

    public AjaxResult success(String message) {
        this.put(SUCCESS, true);
        this.put(MESSAGE, message);
        return this;
    }

    public AjaxResult fail(String message) {
        this.put(SUCCESS, false);
        this.put(MESSAGE, message);
        return this;
    }

    public AjaxResult redirect(String url) {
        this.put(REDIRECT, url);
        return this;
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
        ajaxResult.success();
        return ajaxResult;
    }

    public static AjaxResult createAjaxResult(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.success();
        ajaxResult.setData(data);
        return ajaxResult;
    }
}
