package ink.kangaroo.job.task;

import ink.kangaroo.common.core.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 15:16
 */
@Component("kangarooTask")
public class KangarooTask {
    public void kangarooMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void kangarooParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void kangarooNoParams() {
        System.out.println("执行无参方法");
    }
}
