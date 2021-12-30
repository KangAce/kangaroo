package ink.kangaroo.douyin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@RestController
@RequestMapping("api/redirect")
public class RedirectController {
    @RequestMapping("auth")
    public String auth(String code, String status) {


        return "";
    }
}
