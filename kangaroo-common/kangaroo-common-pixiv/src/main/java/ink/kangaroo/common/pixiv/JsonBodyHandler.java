package ink.kangaroo.common.pixiv;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.kangaroo.common.core.exception.base.BaseException;
import lombok.AllArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author Kangaroo
 * @version 1.0
 * @date 2019/08/15 20:29
 * @description JsonBodyHandler
 */
@AllArgsConstructor
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final Class<T> type;

    public static <T> JsonBodyHandler jsonBodyHandler(final Class<T> type) {
        return new JsonBodyHandler(type);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(final HttpResponse.ResponseInfo responseInfo) {
        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofByteArray(),
                byteArray -> {
                    try {
                        /**
                         * 为了解决插画没有系列的时候接口返回false 有系列的时候则返回一个对象，将返回false的时候转换为null
                         */
                        byteArray = (new String(byteArray)
                                .replace("\"illust_series\":false,", "")
                                .replace("\"next\":false,", "")
                                .getBytes(StandardCharsets.UTF_8));
                        return objectMapper.readValue(new ByteArrayInputStream(byteArray), this.type);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //json转换异常
                    throw new BaseException("Json转换出错");
                });
    }
}