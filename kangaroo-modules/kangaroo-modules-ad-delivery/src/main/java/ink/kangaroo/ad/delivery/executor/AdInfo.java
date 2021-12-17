package ink.kangaroo.ad.delivery.executor;

import lombok.Data;

import java.util.List;

/**
 * 保存任务执行过程中 整个广告的执行情况
 * @author kbw
 * @version 1.0
 * @date 2021/9/23 23:56
 */
@Data
public class AdInfo {
    //确定当前广告是否已成功
    private Integer status;

    private String campaignId;

    private Long adUnitId;

    private List<Long> creativeIds;
}
