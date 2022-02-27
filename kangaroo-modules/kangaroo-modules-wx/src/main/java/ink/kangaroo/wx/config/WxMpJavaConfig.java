package ink.kangaroo.wx.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ink.kangaroo.wx.config.properties.WxMpProperties;
import ink.kangaroo.wx.domain.WxMpRouter;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import redis.clients.jedis.JedisPool;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpJavaConfig {

    private final WxMpProperties properties;

    @Bean
    public WxMpService wxMpService() {
        // 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
        final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
        }

        WxMpService service = new WxMpServiceImpl();
        service.setMultiConfigStorages(configs
                .stream().map(a -> {
                    WxMpDefaultConfigImpl configStorage;
                    if (this.properties.isUseRedis()) {
                        final WxMpProperties.RedisConfig redisConfig = this.properties.getRedisConfig();
                        JedisPool jedisPool = new JedisPool(redisConfig.getHost(), redisConfig.getPort());
                        configStorage = new WxMpRedisConfigImpl(new JedisWxRedisOps(jedisPool), a.getAppId());
                    } else {
                        configStorage = new WxMpDefaultConfigImpl();
                    }

                    configStorage.setAppId(a.getAppId());
                    configStorage.setSecret(a.getSecret());
                    configStorage.setToken(a.getToken());
                    configStorage.setAesKey(a.getAesKey());
                    return configStorage;
                }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));
        service.addConfigStorage("", new WxMpDefaultConfigImpl());
        return service;
    }

    @Bean
    public WxMpMaterialService wxMpMaterialService() {
        return new WxMpMaterialServiceImpl(wxMpService());
    }

    @Bean
    public WxMpMessageRouter wxMpMessageRouter() {
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService());
        List<WxMpRouter> routers = new ArrayList<>();
        routers.add(WxMpRouter.builder().id(0L).name("asdf").async(false).msgType(WxConsts.XmlMsgType.TEXT).content("康卜文").replyContent("本公众号的作者").build());
        routers.add(WxMpRouter.builder().id(1L).name("as1df").async(false).msgType(WxConsts.XmlMsgType.TEXT).content("A123").replyContent("asdf").build());
        routers.add(WxMpRouter.builder().id(2L).name("sdfh").async(false).msgType(WxConsts.XmlMsgType.TEXT).content("小飞机").replyContent("" +
                        "小飞机链接\n" +
                        "链接：https://pan.baidu.com/s/1PNMrQOwHr3pksSgW4ER5wg \n" +
                        "提取码：vl5x"
        ).build());

        for (WxMpRouter router : routers) {
            wxMpMessageRouter
                    .rule()
                    .async(router.isAsync())
                    .msgType(router.getMsgType())
                    .content(router.getContent())
                    .handler((wxMessage, context, wxMpService, sessionManager) -> WxMpXmlOutMessage.TEXT()
                            .content(router.getReplyContent())
                            .fromUser(wxMessage.getToUser())
                            .toUser(wxMessage.getFromUser())
                            .build())
                    .end();
        }

        return wxMpMessageRouter;
    }
}
