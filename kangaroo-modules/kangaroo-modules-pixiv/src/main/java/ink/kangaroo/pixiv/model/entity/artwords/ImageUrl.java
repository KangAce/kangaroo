package ink.kangaroo.pixiv.model.entity.artwords;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/11 1:10
 * @description ImageUrl
 */
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
