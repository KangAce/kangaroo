//package ink.kangaroo.wx.controller;
//
//import me.zhyd.oauth.config.AuthConfig;
//import me.zhyd.oauth.request.AuthWeChatOpenRequest;
//import me.zhyd.oauth.model.AuthCallback;
//import me.zhyd.oauth.request.AuthRequest;
//import me.zhyd.oauth.utils.AuthStateUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@RestController
//@RequestMapping("/oauth")
//public class RestAuthController {
//
//    @RequestMapping("/render")
//    public void renderAuth(HttpServletResponse response) throws IOException {
//        AuthRequest authRequest = getAuthRequest();
//        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
//    }
//
//    @RequestMapping("/callback")
//    public Object login(AuthCallback callback) {
//        AuthRequest authRequest = getAuthRequest();
//        return authRequest.login(callback);
//    }
//
//    private AuthRequest getAuthRequest() {
//        return new AuthWeChatOpenRequest(AuthConfig.builder()
//                .clientId("Client ID")
//                .clientSecret("Client Secret")
//                .redirectUri("https://www.zhyd.me/oauth/callback/wechat")
//                .build());
//    }
//}