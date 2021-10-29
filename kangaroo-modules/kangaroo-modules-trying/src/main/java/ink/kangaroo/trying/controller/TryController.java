package ink.kangaroo.trying.controller;

import ink.kangaroo.common.core.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/10/28 16:02
 */
@RequestMapping("try")
@RestController
public class TryController {


    @GetMapping("try")
    public String trys(String orderId, String order_id, String orderStatus, String order_status) {
        System.out.println(orderId + order_id + orderStatus + order_status);
        return DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss");
    }

}
