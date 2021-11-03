package ink.kangaroo.common.pixiv.model.discovery;

import lombok.Data;

@Data
public class DiscoveryPixivParam {
    private PixivDiscoveryCategory category;
    private String mode;
    private String limit;
    private String lang;
}
