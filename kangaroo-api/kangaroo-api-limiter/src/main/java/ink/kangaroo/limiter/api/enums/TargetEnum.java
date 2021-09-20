package ink.kangaroo.limiter.api.enums;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/10 15:43
 */
public enum TargetEnum {

    /**
     * 字节跳动订单列表，每个appKey每分钟设阀60次
     */
    BYTEDANCE_LIST(60),
    /**
     * 快手订单列表
     */
    KWAI_LIST(60),
    /**
     * 枫页订单列表100/分钟
     */
    FYEC_LIST(990),
    /**
     * 枫页同步快递信息500/分钟
     */
    FYEC_SEND(900),
    /**
     * 枫页每个接口限制1000/分钟
     */
    FYEC_IP(990),
    /**
     * 枫页关闭订单100/分钟
     */
    FYEC_CLOSE(900),
    /**
     * 获取图片信息 限制2000次每分钟
     */
    TX_IMAGE_GET(1800),
    /**
     * 获取日报表 限制2000次每分钟
     */
    TX_DAILY_REPORTS_GET(1800),
    /**
     * 获取视频素材 限制2000次每分钟
     */
    TX_VIDEOS_GET(1800);
    ;

    private final int limit;

    TargetEnum(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
