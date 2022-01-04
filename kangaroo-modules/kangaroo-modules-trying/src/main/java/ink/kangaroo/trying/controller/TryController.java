//package ink.kangaroo.trying.controller;
//
//import com.alibaba.fastjson.JSON;
//import ink.kangaroo.common.core.utils.DateUtils;
//import ink.kangaroo.common.core.utils.JwtTokenUtil;
//import ink.kangaroo.trying.domain.PushOrderInfoVo;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author kbw
// * @version 1.0
// * @date 2021/10/28 16:02
// */
//@RequestMapping("try")
//@RestController
//public class TryController {
//
//
//    @PostMapping("try")
//    public String trys(@RequestBody PushOrderInfoVo pushOrderInfoVo) {
//        System.out.println(pushOrderInfoVo);
////        String s = "{'code':0,'message':'success','timestamp':'" + DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss") + "'}";
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 0);
//        result.put("message", "success");
//        result.put("timestamp", DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"));
//        String s = JSON.toJSONString(result);
//        return s;
//    }
//
////    @GetMapping("try")
////    public String trys(String orderId, String order_id, String orderStatus, String order_status) {
////        System.out.println(orderId + order_id + orderStatus + order_status);
////        return DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss");
////    }
//
//}
