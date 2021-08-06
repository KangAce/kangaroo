package ink.kangaroo.common.log.annotation;

import ink.kangaroo.common.log.enums.BusinessType;
import ink.kangaroo.common.log.enums.OperatorType;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:14
 */
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
