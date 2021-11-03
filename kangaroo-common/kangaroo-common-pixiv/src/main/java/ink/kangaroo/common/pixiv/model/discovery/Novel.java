package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Novel {

    @JsonSetter("updateDate")
    @JSONField(name = "updateDate")
    private String updateDate;

    @JsonSetter("bookmarkData")
    @JSONField(name = "bookmarkData")
    private Object bookmarkData;

    @JsonSetter("isBookmarkable")
    @JSONField(name = "isBookmarkable")
    private Boolean isBookmarkable;

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

    @JsonSetter("seriesId")
    @JSONField(name = "seriesId")
    private String seriesId;

    @JsonSetter("tags")
    @JSONField(name = "tags")
    private List<String> tags;

    @JsonSetter("seriesTitle")
    @JSONField(name = "seriesTitle")
    private String seriesTitle;

    @JsonSetter("isOriginal")
    @JSONField(name = "isOriginal")
    private Boolean isOriginal;

    @JsonSetter("marker")
    @JSONField(name = "marker")
    private Object marker;

    @JsonSetter("isUnlisted")
    @JSONField(name = "isUnlisted")
    private Boolean isUnlisted;

    @JsonSetter("bookmarkCount")
    @JSONField(name = "bookmarkCount")
    private Integer bookmarkCount;

    @JsonSetter("textCount")
    @JSONField(name = "textCount")
    private Integer textCount;

    @JsonSetter("titleCaptionTranslation")
    @JSONField(name = "titleCaptionTranslation")
    private TitleCaptionTranslation titleCaptionTranslation;

    @JsonSetter("id")
    @JSONField(name = "id")
    private String id;

    @JsonSetter("profileImageUrl")
    @JSONField(name = "profileImageUrl")
    private String profileImageUrl;

    @JsonSetter("createDate")
    @JSONField(name = "createDate")
    private String createDate;

}
