package ink.kangaroo.common.pixiv;

import lombok.AllArgsConstructor;

import java.net.http.HttpResponse;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/15 20:29
 * @description JsonBodyHandler
 */
@AllArgsConstructor
public class InputStreamBodyHandler<T> implements HttpResponse.BodyHandler<T> {

    private final Class<T> type;

    public static <T> InputStreamBodyHandler inputStreamBodyHandler(final Class<T> type) {
        return new InputStreamBodyHandler(type);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(final HttpResponse.ResponseInfo responseInfo) {
        return (HttpResponse.BodySubscriber<T>) HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofInputStream(),
                inputStream -> {
                    return inputStream;
                });
    }
}