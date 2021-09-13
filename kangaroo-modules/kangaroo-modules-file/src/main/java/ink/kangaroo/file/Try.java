package ink.kangaroo.file;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/9 15:19:23
 */
public class Try {
    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(300, 400);

        Map<String, Integer> successAndFailCount =  new HashMap<>();
        successAndFailCount.put("error_code",300);
        System.out.println(integers.contains(successAndFailCount.get("error_code")));
    }
}
