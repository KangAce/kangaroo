package ink.kangaroo.common.pixiv.model.illust;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class ImageUrl {
    @JsonSetter("thumb_mini")
    private String thumbMini;
    @JsonSetter("small")
    private String small;
    @JsonSetter("regular")
    private String regular;
    @JsonSetter("original")
    private String original;
}
