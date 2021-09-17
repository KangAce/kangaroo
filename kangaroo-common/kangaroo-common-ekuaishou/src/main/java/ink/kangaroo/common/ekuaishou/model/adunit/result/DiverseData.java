/**
 * Copyright 2021 bejson.com
 */
package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 应用信息
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class DiverseData {

    /**
     * 应用名称
     * <p>
     * app_name
     */
    @JSONField(name = "app_name")
    private String appName;
    /**
     * 应用包名
     * <p>
     * app_package_name
     */
    @JSONField(name = "app_package_name")
    private String AppPackageName;
    /**
     * 应用操作系统类型
     * 0：未知，1：ANDROID，2：IO
     * device_os_type
     */
    @JSONField(name = "device_os_type")
    private int deviceOsType;

}