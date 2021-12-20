package ink.kangaroo.wx.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import ink.kangaroo.common.core.domain.AuthToken;
import ink.kangaroo.common.core.utils.JwtTokenUtil;
import ink.kangaroo.common.core.utils.json.JsonUtils;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.system.api.RemoteUserService;
import ink.kangaroo.wx.config.WxMaConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
@Slf4j
public class WxMaUserController {

    private final RemoteUserService remoteUserService;

    public WxMaUserController(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public AjaxResult getCode(@PathVariable String appid, String code, String nickName, Integer gender, String language, String city, String avatarUrl) {
        if (StringUtils.isBlank(code)) {
            return AjaxResult.fail("");
        }
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getUnionid());
            log.info(session.getOpenid());
            //向缓存中存入登录信息，以openid为key jwt为value
            AuthToken authToken = remoteUserService.loginMaByOpenId(appid, session.getOpenid(), session.getUnionid(), session.getSessionKey(), nickName, avatarUrl);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            authToken.setRefreshToken("no");

            return AjaxResult.createAjaxResult(authToken);
//            return session.getOpenid();
        } catch (WxErrorException /*| JsonProcessingException*/ e) {
            log.error(e.getMessage(), e);
            return AjaxResult.fail("失败");
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        try {
            return JsonUtils.objectToJson(userInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        try {
            return JsonUtils.objectToJson(phoneNoInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
