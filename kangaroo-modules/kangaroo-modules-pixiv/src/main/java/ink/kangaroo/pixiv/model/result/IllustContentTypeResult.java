package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class IllustContentTypeResult {
    @JsonSetter("sexual")
    private Integer sexual;
    @JsonSetter("lo")
    private Boolean lo;
    @JsonSetter("grotesque")
    private Boolean grotesque;
    @JsonSetter("violent")
    private Boolean violent;
    @JsonSetter("homosexual")
    private Boolean homosexual;
    @JsonSetter("drug")
    private Boolean drug;
    @JsonSetter("thoughts")
    private Boolean thoughts;
    @JsonSetter("antisocial")
    private Boolean antisocial;
    @JsonSetter("religion")
    private Boolean religion;
    @JsonSetter("original")
    private Boolean original;
    @JsonSetter("furry")
    private Boolean furry;
    @JsonSetter("bl")
    private Boolean bl;
    @JsonSetter("yuri")
    private Boolean yuri;
}
