package ink.kangaroo.common.pixiv.model.illust;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * https://www.pixiv.net/ajax/illust/86758412
 */
@Data
public class PixivIllustDetailResult {
    /**
     * illustId: "86758412",
     *
     */
    @JsonIgnore
    @JsonSetter("illustId")
    String illustId;
    /**
     * illustTitle: "アズールレーン",
     *
     */
    @JsonIgnore
    @JsonSetter("illustTitle")
    String illustTitle;
    /**
     * illustComment: "",
     *
     */
    @JsonIgnore
    @JsonSetter("illustComment")
    String illustComment;
    /**
     * id: "86758412",
     *
     */

    @JsonIgnore
    @JsonSetter("id")
    String id;
    /**
     * title: "アズールレーン",
     *
     */
    @JsonIgnore
    @JsonSetter("title")
    String title;
    /**
     * description: "",
     *
     */
    @JsonIgnore
    @JsonSetter("description")
    String description;
    /**
     * illustType: 0,
     *
     */

    @JsonIgnore
    @JsonSetter("illustType")
    Integer illustType;
    /**
     * createDate: "2021-01-02T07:26:35+00:00",
     *
     */
    @JsonIgnore
    @JsonSetter("createDate")
    String createDate;
    /**
     * uploadDate: "2021-01-02T07:26:35+00:00",
     *
     */
    @JsonIgnore
    @JsonSetter("uploadDate")
    String uploadDate;
    /**
     * restrict: 0,
     *
     */
    @JsonIgnore
    @JsonSetter("restrict")
    Integer restrict;
    /**
     * xRestrict: 0,
     *
     */
    @JsonIgnore
    @JsonSetter("xRestrict")
    Integer xRestrict;
    /**
     * sl: 6,
     *
     */
    @JsonIgnore
    @JsonSetter("sl")
    Integer sanityLevel;
}
