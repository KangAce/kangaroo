/**
  * Copyright 2021 bejson.com 
  */
package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Auto-generated: 2021-09-16 16:27:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BackflowForecast {

    /**
     *回流预估值的下限
     *
     * backflow_cv_lower
     */@JSONField(name = "backflow_cv_lower")
    private int backflowCvLower;
    /**
     *回流预估值的上限
     *
     * backflow_cv_upper
     */@JSONField(name = "backflow_cv_upper")
    private int backflowCvUpper;
    /**
     *本次回流预估数据的时间戳，13 位毫秒时间戳
     *
     * backflow_timestamp
     */@JSONField(name = "backflow_timestamp")
    private int backflowTimestamp;
    /**
     *回流预估总金额
     *
     * backflow_payment
     */@JSONField(name = "backflow_payment")
    private String backflowPayment;
    /**
     *回流首日预估 ROI
     *
     * backflow_roi
     */@JSONField(name = "backflow_roi")
    private String backflowRoi;

}