package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import ink.kangaroo.pixiv.model.entity.artwords.ImageUrl;
import ink.kangaroo.pixiv.utils.RequestUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * String url = "https://www.pixiv.net/ajax/illust/" + IllustId + "/pages?lang=" + lang;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IllustPageResult {
    @JsonSetter("urls")
    private ImageUrl imageUrl;
    @JsonSetter("width")
    private Integer width;
    @JsonSetter("height")
    private Integer height;

    public static List<IllustPageResult> getResult(RequestUtil requestUtil, String IllustId, String lang) {
//        IllustPageResult.class.cast()
        String url = "https://www.pixiv.net/ajax/illust/" + IllustId + "/pages?lang=" + lang;
        //获取
        PixivResult<List<IllustPageResult>> rankResult = (PixivResult<List<IllustPageResult>>) requestUtil.getJsonSync(url, PixivResult.class);

        return rankResult.getBody();
/*        Map rankResult = (Map) requestUtil.getJsonSync(url, HashMap.class);
        List<LinkedHashMap> body = (List<LinkedHashMap>) rankResult.get("body");
        return body.stream().map(e -> {
            try {
                *//**
         * 解决LinkedHashMap转换就会出错的问题
         *//*
                objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                String s = JSON.toJSONString(e);
                System.out.println(s);
                return objectMapper.readValue(JSON.toJSONBytes(e), IllustPageResult.class);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());*/
    }
}
