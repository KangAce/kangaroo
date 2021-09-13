package ink.kangaroo.common.ekuaishou.model.adunit.result;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 16:21
 */
public class CreateAdUnitResult {
    /**
     * 	广告组ID
     * unit_id
     */
    @JsonAlias("unit_id")
    private Long unitId;
}
