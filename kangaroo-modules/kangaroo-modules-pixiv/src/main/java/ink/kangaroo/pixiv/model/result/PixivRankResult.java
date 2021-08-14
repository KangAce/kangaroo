package ink.kangaroo.pixiv.model.result;

import com.fasterxml.jackson.annotation.JsonSetter;
import ink.kangaroo.pixiv.utils.RequestUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * String url = "https://www.pixiv.net/ranking.php?mode=" + mode + "&date=" + date +"&p=" + pageNumber + "&format=json";
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

    public static PixivRankResult getResult(RequestUtil requestUtil, String mode, String content, String date, Integer pageNumber, Integer pageSize){
        String url = "https://www.pixiv.net/ranking.php?mode=" + mode + "&date=" + date +"&p=" + pageNumber + "&format=json";
        if (content!=null&&!"all".equals(content)){
            url +="&content="+ content;
        }
        //获取

        PixivRankResult rankResult = (PixivRankResult) requestUtil.getJsonSync(url, PixivRankResult.class);
        return rankResult;

    }
}
