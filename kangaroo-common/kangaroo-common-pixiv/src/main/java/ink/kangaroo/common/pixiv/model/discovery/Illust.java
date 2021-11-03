package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Illust {

    @JsonSetter("updateDate")
    @JSONField(name = "updateDate")
    private String updateDate;

    @JsonSetter("bookmarkData")
    @JSONField(name = "bookmarkData")
    private Object bookmarkData;

    @JsonSetter("description")
    @JSONField(name = "description")
    private String description;

    @JsonSetter("xRestrict")
    @JSONField(name = "xRestrict")
    private Integer xRestrict;

    @JsonSetter("title")
    @JSONField(name = "title")
    private String title;

    @JsonSetter("restrict")
    @JSONField(name = "restrict")
    private Integer restrict;

    @JsonSetter("seriesId")
    @JSONField(name = "seriesId")
    private String seriesId;

    @JsonSetter("seriesTitle")
    @JSONField(name = "seriesTitle")
    private String seriesTitle;

    @JsonSetter("urls")
    @JSONField(name = "urls")
    private Urls urls;

    @JsonSetter("illustType")
    @JSONField(name = "illustType")
    private Integer illustType;

    @JsonSetter("sl")
    @JSONField(name = "sl")
    private Integer sl;

    @JsonSetter("titleCaptionTranslation")
    @JSONField(name = "titleCaptionTranslation")
    private TitleCaptionTranslation titleCaptionTranslation;

    @JsonSetter("id")
    @JSONField(name = "id")
    private String id;

    @JsonSetter("profileImageUrl")
    @JSONField(name = "profileImageUrl")
    private String profileImageUrl;

    @JsonSetter("height")
    @JSONField(name = "height")
    private Integer height;

    @JsonSetter("createDate")
    @JSONField(name = "createDate")
    private String createDate;

    @JsonSetter("pageCount")
    @JSONField(name = "pageCount")
    private Integer pageCount;

    @JsonSetter("isBookmarkable")
    @JSONField(name = "isBookmarkable")
    private Boolean isBookmarkable;

    @JsonSetter("alt")
    @JSONField(name = "alt")
    private String alt;

    @JsonSetter("userName")
    @JSONField(name = "userName")
    private String userName;

    @JsonSetter("isMasked")
    @JSONField(name = "isMasked")
    private Boolean isMasked;

    @JsonSetter("userId")
    @JSONField(name = "userId")
    private String userId;

    @JsonSetter("url")
    @JSONField(name = "url")
    private String url;

    @JsonSetter("tags")
    @JSONField(name = "tags")
    private List<String> tags;

    @JsonSetter("isUnlisted")
    @JSONField(name = "isUnlisted")
    private Boolean isUnlisted;

    @JsonSetter("width")
    @JSONField(name = "width")
    private Integer width;

}
