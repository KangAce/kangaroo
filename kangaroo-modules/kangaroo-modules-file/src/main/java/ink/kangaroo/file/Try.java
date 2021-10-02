package ink.kangaroo.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ink.kangaroo.common.core.utils.file.FileTypeUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
    public static void main(String[] args) throws IOException {

//        List<Integer> integers = Arrays.asList(300, 400);
//
//        Map<String, Integer> successAndFailCount =  new HashMap<>();
//        successAndFailCount.put("error_code",300);
//        System.out.println(integers.contains(successAndFailCount.get("error_code")));

        String s = FileUtils.readFileToString(new File("D:\\4.txt"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
    }
}
