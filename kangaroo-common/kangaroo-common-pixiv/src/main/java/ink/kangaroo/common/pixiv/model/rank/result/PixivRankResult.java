package ink.kangaroo.common.pixiv.model.rank.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import ink.kangaroo.common.pixiv.config.PixivClient;
import ink.kangaroo.common.pixiv.config.PixivProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

/**
 * 获取pixiv排行榜
 * * String url = "https://www.pixiv.net/ranking.php?mode=" + mode + "&date=" + date +"&p=" + pageNumber + "&format=json";
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixivRankResult {
    @JsonSetter("mode")
    private String mode;
    @JsonSetter("next")
    private Integer next;
    @JsonSetter("date")
    private String date;
    @JsonSetter("rank_total")
    private Integer rankTotal;
    @JsonSetter("contents")
    private List<PixivRankContentResult> contents;
    @JsonSetter("prev")
    private String prev;
    @JsonSetter("page")
    private Integer page;
    @JsonSetter("prev_date")
    private String prevDate;
    @JsonSetter("content")
    private String content;
    @JsonSetter("next_date")
    private String nextDate;

    public static PixivRankResult getResult(String mode, String content, String date, Integer pageNumber, Integer pageSize) throws IOException {
        String url = "https://www.pixiv.net/ranking.php?mode=" + mode + "&date=" + date + "&p=" + pageNumber + "&format=json";
        if (content != null && !"all".equals(content)) {
            url += "&content=" + content;
        }
        PixivProperties pixivProperties = new PixivProperties();
        pixivProperties.setCookie("63063042_bzdxnk6SGKaNg4DsdSJ4A853VSZcjOvr");
        PixivClient init = PixivClient.init(pixivProperties);
//        PixivRankResult jsonSync = (PixivRankResult) init.getJsonSync(url, PixivRankResult.class);
//        System.out.println(jsonSync);
//        //获取
//        HttpHeaders httpHeaders = new HttpHeaders();
//        List<String> cookieList = new ArrayList<>();
//        cookieList.add("PHPSESSID=63063042_bzdxnk6SGKaNg4DsdSJ4A853VSZcjOvr");
//        httpHeaders.put("Cookie", cookieList);
//        ResponseEntity<PixivRankResult> pixivRankResultResponseEntity = RestTemplateUtils.get(url, httpHeaders, PixivRankResult.class, new Object());
////        ResponseEntity<PixivRankResult> pixivRankResultResponseEntity = RestTemplateUtils.get(url, PixivRankResult.class);
//        if (pixivRankResultResponseEntity.getStatusCode().is2xxSuccessful()) {
//            PixivRankResult rankResult = pixivRankResultResponseEntity.getBody();
//            return rankResult;
//        } else {
//            return null;
//        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(getResult("daily", "all", "20211010", 1, 2));
    }
//    public static PixivRankResult getResult(RequestUtil requestUtil, String mode, String content, String date, Integer pageNumber, Integer pageSize){
//        String url = "https://www.pixiv.net/ranking.php?mode=" + mode + "&date=" + date +"&p=" + pageNumber + "&format=json";
//        if (content!=null&&!"all".equals(content)){
//            url +="&content="+ content;
//        }
//        //获取
//
//        PixivRankResult rankResult = (PixivRankResult) requestUtil.getJsonSync(url, PixivRankResult.class);
//        return rankResult;
//
//    }

}
