package ink.kangaroo.pixiv.model.result.artwork;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.kangaroo.pixiv.model.result.PixivResult;
import ink.kangaroo.pixiv.utils.RequestUtil;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * https://www.pixiv.net/ajax/illust/86758412
 */
@Data
public class PixivArtworkDetailResult {
    /**
     * illustId: "86758412",
     *
     */
    @JsonSetter("illustId")
    String illustId;
    /**
     * illustTitle: "アズールレーン",
     *
     */
    @JsonSetter("illustTitle")
    String illustTitle;
    /**
     * illustComment: "",
     *
     */
    @JsonSetter("illustComment")
    String illustComment;
    /**
     * id: "86758412",
     *
     */
    @JsonSetter("id")
    String id;
    /**
     * title: "アズールレーン",
     *
     */
    @JsonSetter("title")
    String title;
    /**
     * description: "",
     *
     */
    @JsonSetter("description")
    String description;
    /**
     * illustType: 0,
     *
     */
    @JsonSetter("illustType")
    Integer illustType;
    /**
     * createDate: "2021-01-02T07:26:35+00:00",
     *
     */
    @JsonSetter("createDate")
    String createDate;
    /**
     * uploadDate: "2021-01-02T07:26:35+00:00",
     *
     */
    @JsonSetter("uploadDate")
    String uploadDate;
    /**
     * restrict: 0,
     *
     */
    @JsonSetter("restrict")
    Integer restrict;
    /**
     * xRestrict: 0,
     *
     */
    @JsonSetter("xRestrict")
    Integer xRestrict;
    /**
     * sl: 6,
     *
     */
    @JsonSetter("sl")
    Integer sanityLevel;

    public static PixivArtworkDetailResult getInstance(RequestUtil requestUtil, String artwordId, ObjectMapper objectMapper){
        Assert.notNull(artwordId,"artwordId must be not null");
        PixivResult<Object> jsonSync = (PixivResult<Object>) requestUtil.getJsonSync("https://www.pixiv.net/ajax/illust/" + artwordId, PixivResult.class);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            if(jsonSync==null){
                return null;
            }
            return objectMapper.readValue(JSON.toJSONBytes(jsonSync.getBody()), PixivArtworkDetailResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
