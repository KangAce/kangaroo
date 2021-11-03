package ink.kangaroo.common.pixiv.model.discovery;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Users {

    @JsonSetter("image")
    @JSONField(name = "image")
    private String image;

    @JsonSetter("isBlocking")
    @JSONField(name = "isBlocking")
    private Boolean isBlocking;

    @JsonSetter("acceptRequest")
    @JSONField(name = "acceptRequest")
    private Boolean acceptRequest;

    @JsonSetter("imageBig")
    @JSONField(name = "imageBig")
    private String imageBig;

    @JsonSetter("userId")
    @JSONField(name = "userId")
    private String userId;

    @JsonSetter("isFollowed")
    @JSONField(name = "isFollowed")
    private Boolean isFollowed;

    @JsonSetter("premium")
    @JSONField(name = "premium")
    private Boolean premium;

    @JsonSetter("background")
    @JSONField(name = "background")
    private Object background;

    @JsonSetter("name")
    @JSONField(name = "name")
    private String name;

    @JsonSetter("isMypixiv")
    @JSONField(name = "isMypixiv")
    private Boolean isMypixiv;

    @JsonSetter("followedBack")
    @JSONField(name = "followedBack")
    private Boolean followedBack;

    @JsonSetter("comment")
    @JSONField(name = "comment")
    private String comment;

    @JsonSetter("partial")
    @JSONField(name = "partial")
    private Integer partial;

}
