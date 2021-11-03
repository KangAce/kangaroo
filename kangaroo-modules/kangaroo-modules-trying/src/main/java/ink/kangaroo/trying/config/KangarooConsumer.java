//package ink.kangaroo.trying.config;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RocketMQMessageListener(topic = "kangaroo-try", consumerGroup = "kangaroo-group")
//public class KangarooConsumer implements RocketMQListener<Message> {
//    @Override
//    public void onMessage(Message message) {
//        log.info("received message: {}", message);
////        log.info("received message: {}", (String) JSON.parseObject(message.getBody(), String.class));
//        log.info("received message: {}", new String(message.getBody()));
//    }
//}