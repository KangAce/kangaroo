package ink.kangaroo.pixiv.config;

import ink.kangaroo.common.core.constant.SecurityConstants;
import ink.kangaroo.common.pixiv.config.PixivClient;
import ink.kangaroo.common.pixiv.config.PixivProperties;
import ink.kangaroo.system.api.RemoteConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class PixivClientConfig {

    @Resource
    RemoteConfigService remoteConfigService;

    @Bean("pixivClient")
    PixivClient getPixivClient() {
        PixivClient pixivClient = null;
        PixivProperties pixivProperties = new PixivProperties();
        String data = remoteConfigService.getConfigKey("pixiv.cookie", SecurityConstants.INNER).getMessage();
        System.out.println(data);
        pixivProperties.setCookie(data);
        pixivProperties.setProxyIp("127.0.0.1");
        pixivProperties.setProxyPort(1080);
        try {
            pixivClient = PixivClient.init(pixivProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pixivClient;
    }
}
