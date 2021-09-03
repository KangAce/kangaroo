package ink.kangaroo.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/3 18:22
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("welcome")
    public String welcome(Model model) {
        return "index/welcome";
    }

    @RequestMapping("status")
    @ResponseBody
    public String status() {
        return "SUCCESS";
    }

    @RequestMapping("logout")
    public String logout() {
        return "redirct:logout";
    }


}
