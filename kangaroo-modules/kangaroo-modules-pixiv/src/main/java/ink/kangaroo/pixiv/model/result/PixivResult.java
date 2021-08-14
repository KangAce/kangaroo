package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class PixivResult<T> {
    @JsonSetter("body")
    private T body;
    @JsonSetter("error")
    private Boolean error;
    @JsonSetter("message")
    private String message;

}
