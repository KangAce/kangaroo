package ink.kangaroo.common.pixiv.model.illust;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * String url = "https://www.pixiv.net/ajax/illust/" + IllustId + "/pages?lang=" + lang;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixivIllustPageResult {
    @JSONField(name = "urls")
    private ImageUrl imageUrl;
    @JSONField(name = "width")
    private Integer width;
    @JSONField(name = "height")
    private Integer height;

//    public static List<PixivIllustPageResult> getResult(RequestUtil requestUtil, String IllustId, String lang) {
////        IllustPageResult.class.cast()
//        String url = "https://www.pixiv.net/ajax/illust/" + IllustId + "/pages?lang=" + lang;
//        //获取
//        PixivResult<List<PixivIllustPageResult>> rankResult = (PixivResult<List<PixivIllustPageResult>>) requestUtil.getJsonSync(url, PixivResult.class);
//
//        return rankResult.getBody();
///*        Map rankResult = (Map) requestUtil.getJsonSync(url, HashMap.class);
//        List<LinkedHashMap> body = (List<LinkedHashMap>) rankResult.get("body");
//        return body.stream().map(e -> {
//            try {
//                *//**
//         * 解决LinkedHashMap转换就会出错的问题
//         *//*
//                objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//                String s = JSON.toJSONString(e);
//                System.out.println(s);
//                return objectMapper.readValue(JSON.toJSONBytes(e), IllustPageResult.class);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            return null;
//        }).collect(Collectors.toList());*/
//    }
}
