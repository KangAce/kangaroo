package ink.kangaroo.common.pixiv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pixiv.personal")
public class PixivProperties {
    private String cookie;
}
