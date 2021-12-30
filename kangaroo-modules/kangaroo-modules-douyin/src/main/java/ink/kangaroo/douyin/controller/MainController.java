package ink.kangaroo.douyin.controller;

import ink.kangaroo.common.core.utils.UrlUtil;
import ink.kangaroo.douyin.domain.PlatformType;
import ink.kangaroo.douyin.open.api.DyOpenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@RestController
@RequestMapping("api/auth")
public class MainController {


    @RequestMapping("auth")
    public void auth(HttpServletResponse httpServletResponse, Integer authType, String authName, String remark) {
//        String douyinBaseUrl = "https://open.douyin.com/oauth/access_token";
//        String toutiaoBaseUrl = "https://open.snssdk.com/oauth/access_token";
//        String xiguaBaseUrl = "https://open-api.ixigua.com/oauth/connect";
        String douyinBaseUrl = "https://open.douyin.com/platform/oauth/connect/";
        String toutiaoBaseUrl = "https://open.snssdk.com/oauth/authorize/";
        String xiguaBaseUrl = "https://open-api.ixigua.com/oauth/connect";
        //client_key	 应用的唯一标识
        //response_type 写死为'code'即可
        //scope 应用授权作用域,多个授权作用域以英文逗号（,）分隔
        // //optionalScope 应用授权可选作用域,多个授权作用域以英文逗号（,）分隔，每一个授权作用域后需要加上一个是否默认勾选的参数，1为默认勾选，0为默认不勾选
        //redirect_uri 授权成功后的回调地址，必须以http/https开头。域名必须对应申请应用时填写的域名，如不清楚请联系应用申请人。
        //state 用于保持请求和回调的状态
        Map<String, String> params = new HashMap<>();
        params.put("client_key", "");
        params.put("response_type", "code");
        params.put("scope", "");
        params.put("optionalScope", "");
        params.put("redirect_uri", "https://www.imore.fun/kangaroo/douyin/platform/authorization/redirect");
        params.put("state", authName + ":" + authType);
        String get = UrlUtil.getParams("get", params);
        PlatformType byCode = PlatformType.getByCode(authType);
        String projectUrl = "/";
        switch (byCode) {
            case DOUYIN:
                projectUrl = douyinBaseUrl + get;
                break;
            case XIGUA:
                projectUrl = xiguaBaseUrl + get;
                break;
            case TOUTIAO:
                projectUrl = toutiaoBaseUrl + get;
                break;
            default:
                break;
        }
        httpServletResponse.setHeader("Location", projectUrl);

        httpServletResponse.setStatus(302);

    }

//    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
//    public void method(HttpServletResponse httpServletResponse) {
//        String douyinBaseUrl = "https://open.douyin.com/oauth/access_token";
//        String toutiaoBaseUrl = "https://open.snssdk.com/oauth/access_token";
//        String xiguaBaseUrl = "https://open-api.ixigua.com/oauth/connect";
//        //client_key	 应用的唯一标识
//        //response_type 写死为'code'即可
//        //scope 应用授权作用域,多个授权作用域以英文逗号（,）分隔
//        // //optionalScope 应用授权可选作用域,多个授权作用域以英文逗号（,）分隔，每一个授权作用域后需要加上一个是否默认勾选的参数，1为默认勾选，0为默认不勾选
//        //redirect_uri 授权成功后的回调地址，必须以http/https开头。域名必须对应申请应用时填写的域名，如不清楚请联系应用申请人。
//        //state 用于保持请求和回调的状态
//        Map<String, String> params = new HashMap<>();
//        params.put("client_key", "");
//        params.put("response_type", "");
//        params.put("scope", "");
//        params.put("optionalScope", "");
//        params.put("redirect_uri", "");
//        params.put("state", "");
//        UrlUtil.getParams("get", params);
//        if ()
//            httpServletResponse.setHeader("Location", projectUrl);
//
//        httpServletResponse.setStatus(302);
//
//    }
//
//    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
//    public ModelAndView method() {
//
//        return new ModelAndView("redirect:" + projectUrl);
//
//    }
}
