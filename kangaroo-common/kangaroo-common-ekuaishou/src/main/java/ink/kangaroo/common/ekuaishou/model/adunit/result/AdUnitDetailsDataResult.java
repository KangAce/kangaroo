/**
 * Copyright 2021 bejson.com
 */
package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2021-09-16 16:27:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class AdUnitDetailsDataResult {

    /**
     *
     *
     * total_count
     */
    @JSONField(name = "total_count")
    private int totalCount;
    /**
     *
     *
     * details
     */
    @JSONField(name = "details")
    private List<AdUnitDetailsResult> details;


}