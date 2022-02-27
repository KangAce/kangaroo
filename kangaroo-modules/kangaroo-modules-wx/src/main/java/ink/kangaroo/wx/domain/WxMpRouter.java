package ink.kangaroo.wx.domain;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@Data
@Builder
public class WxMpRouter extends BaseEntity {
    private Long id;

    private String name;

    private String appId;

    private boolean async;

    private String msgType;

    private String content;

    private String replyContent;
}
